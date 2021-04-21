package app.controller;


import app.TextQuestApplication;
import app.controller.oauth.OauthController;
import app.controller.oauth.util.constant.PropName;
import app.controller.oauth.util.constant.Provider;
import app.controller.oauth.util.constant.ReqParam;
import app.controller.oauth.util.constant.SecureParam;
import app.controller.oauth.util.request.UrlBuilder;
import app.controller.util.CookieUtil;
import app.controller.util.constant.Period;
import app.controller.util.constant.Status;
import app.controller.util.exception.missedToken.MissedTokenException;
import app.controller.util.exception.missedToken.types.MissedRefreshException;
import app.controller.util.json.auth.JsonAnswer;
import app.controller.util.json.auth.JsonForm;
import app.database.model.*;
import app.database.model.node.types.LinkedNode.LinkedNode;
import app.database.service.NodeService;
import app.database.service.VarService;
import app.database.util.enums.Role;
import app.security.util.tokenUtil.JwtUtil;
import app.security.util.tokenUtil.RefreshUtil;
import app.database.model.user.User;
import app.database.model.user.types.MailUser;
import app.database.model.user.types.OauthUser;
import app.database.service.RefreshService;
import app.database.service.userService.UserService;
import app.database.service.userService.types.MailUserService;
import app.database.service.userService.types.OauthUserService;
import app.security.Hash;
import app.security.util.ValidatorUtil;
import app.security.secretFactory.types.EmailTokenFactory;
import app.security.secretFactory.types.StateFactory;
import app.util.AbstractConstant;
import app.util.LoggerFactory;
import app.util.constant.LogType;
import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

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
     * Logger for recording codes and tokens in the process of the oauth authorisation
     */
    protected final static Logger emailLogger = LoggerFactory.getLogger(LogType.EMAIL);
    /**
     * Factory for parsing state - string for identifying user for oauth service
     */
    private final static StateFactory stateFactory = new StateFactory();
    /**
     * Factory for producing email tokens
     */
    private final static EmailTokenFactory tokenFactory = new EmailTokenFactory();
    /**
     * Json parser
     */
    private final static Gson gson = new Gson();

    /**
     * Service for interacting with mail users
     */
    private final MailUserService mailService;

    /**
     * Service for interacting with oauth users
     */
    private final OauthUserService oauthService;

    /**
     * Service for interacting with all types of users
     */
    private final UserService userService;

    /**
     * Service for interacting with refresh tokens
     */
    private final RefreshService refreshService;

    /**
     * Service for interacting with nodes
     */
    private final NodeService nodeService;

    private final VarService varService;

    /**
     * Contains properties for each oauth service
     * [provider name]: [Properties]
     */
    private final HashMap<String, Properties> propsMap;

    /**
     * Class constructor injecting user services
     *
     * @param mailService    injects basic users service
     * @param oauthService   injects oauth users service
     * @param userService    injects all users service
     * @param refreshService injects refresh token service
     * @param nodeService    injects nodes service
     * @param varService
     * @throws IOException if props file doesn't exist
     */
    public AuthController(MailUserService mailService, OauthUserService oauthService, UserService userService, RefreshService refreshService, NodeService nodeService, VarService varService) throws IOException {
        this.mailService = mailService;
        this.oauthService = oauthService;
        this.userService = userService;
        this.refreshService = refreshService;
        this.nodeService = nodeService;
        this.varService = varService;
        this.propsMap = new HashMap<>();
        for (Object providerObj : AbstractConstant.getValues(Provider.class)) {
            String provider = (String) providerObj;
            Properties props = new Properties();
            props.load(new FileInputStream("src/main/resources/props/oauth/" + provider + ".properties"));
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
    @ResponseBody
    @PostMapping(path = "/login", consumes = MimeTypeUtils.APPLICATION_JSON_VALUE, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public String login(@RequestBody JsonForm jsonForm, HttpServletResponse response) {
        JsonAnswer jsonAnswer = new JsonAnswer();
        MailUser user = mailService.getByMail(jsonForm.getMail().toLowerCase());
        if (user == null) {
            jsonAnswer.setMsg(Status.NOT_FOUND);
        } else if (!Hash.check(jsonForm.getPsw(), user.getPsw())) {
            jsonAnswer.setMsg(Status.INVALID_PSW);
        } else {
            jsonAnswer.setAccepted(true);
            attachTokens(response, user);
            emailLogger.info("login: email:" + user.getMail());
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
    @ResponseBody
    @PostMapping(path = "/register", consumes = MimeTypeUtils.APPLICATION_JSON_VALUE, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public String register(@RequestBody JsonForm jsonForm, HttpServletResponse response) {
        JsonAnswer jsonAnswer = new JsonAnswer();
        MailUser user = mailService.getByMail(jsonForm.getMail().toLowerCase());
        if (user != null) {
            jsonAnswer.setMsg(Status.EXISTS);
        } else if (!ValidatorUtil.mail(jsonForm.getMail())) {
            jsonAnswer.setMsg(Status.BAD_EMAIL);
        } else if (!ValidatorUtil.psw(jsonForm.getPsw())) {
            jsonAnswer.setMsg(Status.BAD_PSW);
        } else {
            user = wrapUserTemplate(new MailUser());
            user.setMail(jsonForm.getMail());
            user.setPsw(Hash.crypt(jsonForm.getPsw()));
            user.setToken(tokenFactory.create());
            user.setVerified(false);
            jsonAnswer.setAccepted(true);
            attachTokens(response, user);
            emailLogger.info("register: email:" + user.getMail());
        }
        return gson.toJson(jsonAnswer, jsonAnswer.getClass());
    }

    @ResponseBody
    @PostMapping(path = "/logout", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public JsonAnswer logout(HttpServletRequest request, HttpServletResponse response) {
        JsonAnswer jsonAnswer = null;
        try {
            jsonAnswer = new JsonAnswer();
            String refreshCookie = CookieUtil.get(request, SecureParam.REFRESH);
            if (refreshCookie == null)
                throw new MissedRefreshException();
            Refresh refresh = refreshService.getByValue(refreshCookie);
            if (refresh == null)
                throw new MissedRefreshException();
            refreshService.delete(refresh);
            jsonAnswer.setAccepted(true);
            emailLogger.info("logout: refresh token:" + refreshCookie);
        } catch (MissedTokenException e) {
            jsonAnswer.setMsg(Status.INVALID_TOKENS);
        }
        CookieUtil.remove(response, SecureParam.JWT);
        CookieUtil.remove(response, SecureParam.REFRESH);
        return jsonAnswer;
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
            user = wrapUserTemplate(new OauthUser());
            user.setOauthId(oauthId);
            oauthService.add(user);
            oauthLogger.info("User with id:" + oauthId + " was created");
        } else {
            oauthLogger.info("User with id:" + oauthId + " was in the database");
        }
        attachTokens(response, user);

        return "redirect:/";
    }

    /**
     * Mapping for getting urls for oauth authorising.
     * It attaches state cookie to the response and inserts it
     * into the button's link.
     * Button on the page will redirect user to this link.
     * User will authorise on this service and the service will
     * send request to the {@link OauthController#oauthEndpoint(HttpServletRequest, HttpServletResponse)}
     *
     * @param response response for sending errors
     * @return json map with link for each provider
     * @throws IOException if an input or output error occurs
     */
    @ResponseBody
    @PostMapping(path = "/url", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public HashMap<String, String> getBtnUrl(HttpServletResponse response) throws IOException {
        String state = stateFactory.create();
        CookieUtil.add(response, SecureParam.STATE, state, Period.YEAR);
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
        return urlMap;
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

    private <T extends User> T wrapUserTemplate(T user) {
        user.setSetting(new Setting());
        user.addHistory(new History(user, (LinkedNode) nodeService.getFirst()));
        user.setRole(Role.BASIC);
        for (Var var: varService.getAll()) {
            State state = new State();
            state.setVar(var);
            state.setVal(0);
            user.addState(state);
        }
        return user;
    }
}
