package app.text_quest.controller;


import app.text_quest.TextQuestApplication;
import app.text_quest.controller.oauth.OauthController;
import app.text_quest.controller.oauth.util.constant.PropName;
import app.text_quest.controller.oauth.util.constant.Provider;
import app.text_quest.controller.oauth.util.constant.ReqParam;
import app.text_quest.controller.oauth.util.constant.SecureParam;
import app.text_quest.controller.oauth.util.exception.OauthException;
import app.text_quest.controller.oauth.util.exception.types.MissedStateCookieException;
import app.text_quest.controller.oauth.util.request.UrlBuilder;
import app.text_quest.controller.util.CookieUtil;
import app.text_quest.controller.util.ObjectParser;
import app.text_quest.controller.util.constant.Status;
import app.text_quest.controller.util.json.auth.JsonAnswer;
import app.text_quest.controller.util.json.auth.JsonForm;
import app.text_quest.controller.util.token.JwtUtil;
import app.text_quest.controller.util.token.RefreshUtil;
import app.text_quest.database.model.Refresh;
import app.text_quest.database.model.Setting;
import app.text_quest.database.model.user.User;
import app.text_quest.database.model.user.types.MailUser;
import app.text_quest.database.model.user.types.OauthUser;
import app.text_quest.database.service.userService.UserService;
import app.text_quest.database.service.userService.types.MailUserService;
import app.text_quest.database.service.userService.types.OauthUserService;
import app.text_quest.security.Hash;
import app.text_quest.security.util.Validator;
import app.text_quest.util.AbstractConstant;
import app.text_quest.util.LoggerFactory;
import app.text_quest.util.constant.LogType;
import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

/**
 * Authorisation logic server.
 * <p>
 * Provides mappings for login and registration.
 * <p>
 * Website supports 2 types of authorisation:
 * <ul>
 * <li> Via a email
 * <li> Via an oauth2 service
 * </ul>
 * Requests on types:
 * <ul>
 * <li> <b>email</b> - it accepts requests on login and register
 * <li> <b>oauth</b> - it accepts forwards from the {@link OauthController}
 * </ul>
 * Either it sends parsed url to oauth buttons.
 */
@Controller
@RequestMapping("/auth")
public class AuthController {

    /**
     * Logger for recording codes and tokens in the process of the oauth authorisation
     */
    protected final static Logger oauthLogger = LoggerFactory.getLogger(LogType.OAUTH);

    /**
     * Json parser
     */
    private final static Gson gson = new Gson();

    /**
     * Service for interacting with mail users
     */
    private final MailUserService basicService;

    /**
     * Service for interacting with oauth users
     */
    private final OauthUserService oauthService;

    /**
     * Service for interacting with all types of users
     */
    private final UserService userService;

    /**
     * Contains properties for each oauth service
     * [provider name]: [Properties]
     */
    private final HashMap<String, Properties> propsMap;

    /**
     * Class constructor injecting user services
     *
     * @param basicService injects basic users service
     * @param oauthService injects oauth users service
     * @param userService  injects all users service
     * @throws IOException if props file doesn't exist
     */
    public AuthController(MailUserService basicService, OauthUserService oauthService, UserService userService) throws IOException {
        this.basicService = basicService;
        this.oauthService = oauthService;
        this.userService = userService;
        this.propsMap = new HashMap<>();
        for (Object providerObj : AbstractConstant.getValues(Provider.class)) {
            String provider = (String) providerObj;
            Properties props = new Properties();
            props.load(new FileInputStream("src/main/resources/oauth_props/" + provider + ".properties"));
            propsMap.put(provider, props);
        }
    }

    /**
     * Mapping for login users via an email.
     * Checks existing of the email and validates the password.
     * If it all are right, returns positive answer.
     *
     * @param jsonForm json form, containing email and password
     * @param response response to attach JWT and Refresh tokens
     * @return json answer, containing if login is confirmed and the message
     */
    @PostMapping(path = "/login", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public String login(@RequestBody JsonForm jsonForm, HttpServletResponse response) {
        JsonAnswer jsonAnswer = new JsonAnswer();
        MailUser user = basicService.getByMail(jsonForm.getMail());
        if (user == null) {
            jsonAnswer.setMsg(Status.NOT_FOUND);
        } else if (!Hash.check(jsonForm.getPsw(), user.getPsw())) {
            jsonAnswer.setMsg(Status.INVALID_PSW);
        } else {
            jsonAnswer.setAccepted(true);
            attachTokens(response, user);
        }
        return gson.toJson(jsonAnswer, jsonAnswer.getClass());
    }

    /**
     * Mapping for register users via an email.
     * Checks existing of the email and validates email and password.
     * If it all are right, returns positive answer and saves new user into the database.
     *
     * @param jsonForm json form, containing email and password
     * @param response response to attach JWT and Refresh tokens
     * @return json answer, containing if register is confirmed and the message
     */
    @PostMapping(path = "/register", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public String register(@RequestBody JsonForm jsonForm, HttpServletResponse response) {
        JsonAnswer jsonAnswer = new JsonAnswer();
        MailUser user = basicService.getByMail(jsonForm.getMail());
        if (user != null) {
            jsonAnswer.setMsg(Status.EXISTS);
        } else if (!Validator.mail(jsonForm.getMail())) {
            jsonAnswer.setMsg(Status.BAD_EMAIL);
        } else if (!Validator.psw(jsonForm.getPsw())) {
            jsonAnswer.setMsg(Status.BAD_PSW);
        } else {
            user = new MailUser();
            user.setSetting(new Setting());
            user.setMail(jsonForm.getMail());
            user.setPsw(Hash.crypt(jsonForm.getPsw()));
            user.setVerified(false);
            jsonAnswer.setAccepted(true);
            attachTokens(response, user);
        }
        return gson.toJson(jsonAnswer, jsonAnswer.getClass());
    }

    /**
     * Mapping for oauth authorising. If received oauth id doesn't exist in the database,
     * creates it.
     *
     * @param request  containing oauth id
     * @param response for attaching JWT and Refresh tokens
     * @return redirect to the home page
     */
    @GetMapping("/oauth")
    public String oauth(HttpServletRequest request, HttpServletResponse response) {
        String oauthId = (String) request.getAttribute(SecureParam.OAUTH_ID);
        OauthUser user = oauthService.getByOauthId(oauthId);
        if (user == null) {
            user = new OauthUser();
            user.setOauthId(oauthId);
            user.setSetting(new Setting());
            oauthService.add(user);
            oauthLogger.info("user: " + user);
        }
        attachTokens(response, user);
        return "redirect:/";
    }

    /**
     * Mapping for getting urls for oauth authorising.
     * Button on the page will redirect user to this link.
     * User will authorise on this service and the service will
     * send request to the {@link OauthController#oauthEndpoint(HttpServletRequest, HttpServletResponse)}
     *
     * @param request  containing cookie with state
     * @param response response for sending errors
     * @return json map with link for each provider
     * @throws IOException if an input or output error occurs
     */
    @PostMapping(path = "/url", produces = "application/json")
    @ResponseBody
    public String getBtnUrl(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Cookie stateCookie = CookieUtil.find(request, ReqParam.STATE);
            if (stateCookie == null)
                throw new MissedStateCookieException();
            String state = stateCookie.getValue();
            HashMap<String, String> urlMap = new HashMap<>();
            for (Object providerObj : AbstractConstant.getValues(Provider.class)) {
                String provider = (String) providerObj;
                Properties props = propsMap.get(provider);
                UrlBuilder urlBuilder = new UrlBuilder(props.getProperty(PropName.DOMAIN_AUTH));
                urlBuilder
                        .addParam(ReqParam.CLIENT_ID, props.getProperty(PropName.CLIENT_ID))
                        .addParam(ReqParam.REDIRECT_URI, String.format("%s/oauth/%s",
                                TextQuestApplication.getRootUrl(), provider))
                        .addParam(ReqParam.RESPONSE_TYPE, ReqParam.CODE)
                        .addParam(ReqParam.DISPLAY, "popup")
                        .addParam(ReqParam.STATE, state)
                        .addParam(ReqParam.SCOPE, props.getProperty(PropName.SCOPE));
                urlMap.put(provider, urlBuilder.build());
            }
            return ObjectParser.parse(urlMap);
        } catch (OauthException e) {
            response.sendError(402);
        }
        return null;
    }

    /**
     * Adds cookies to the response:
     * <ul>
     * <li> JWT token
     * <li> Refresh token
     * </ul>
     *
     * @param response for attaching cookies
     * @param user     for parsing JWT token
     */
    private void attachTokens(HttpServletResponse response, User user) {
        Refresh refresh = new Refresh();
        refresh.setValue(RefreshUtil.parse());
        user.addToken(refresh);
        userService.update(user);
        RefreshUtil.attach(response, refresh);
        JwtUtil.attach(response, user);
    }
}
