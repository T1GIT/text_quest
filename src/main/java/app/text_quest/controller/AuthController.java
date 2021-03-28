package app.text_quest.controller;


import app.text_quest.TextQuestApplication;
import app.text_quest.controller.oauth.util.constant.PropName;
import app.text_quest.controller.oauth.util.constant.Provider;
import app.text_quest.controller.oauth.util.constant.ReqParam;
import app.text_quest.controller.oauth.util.constant.SecureParam;
import app.text_quest.controller.oauth.util.exception.OauthException;
import app.text_quest.controller.oauth.util.exception.types.MissedStateCookieException;
import app.text_quest.controller.oauth.util.props.OauthProps;
import app.text_quest.controller.oauth.util.props.OauthPropsFactory;
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
import app.text_quest.util.LoggerFactory;
import app.text_quest.util.constant.LogType;
import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;


@Controller
@RequestMapping("/auth")
public class AuthController {

    protected final static Logger oauthLogger = LoggerFactory.getLogger(LogType.OAUTH);
    private final static OauthPropsFactory propsFactory = new OauthPropsFactory();
    private final static Gson gson = new Gson();
    private final MailUserService basicService;
    private final OauthUserService oauthService;
    private final UserService userService;

    public AuthController(MailUserService basicService, OauthUserService oauthService, UserService userService) {
        this.basicService = basicService;
        this.oauthService = oauthService;
        this.userService = userService;
    }

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

    @PostMapping(path = "/url", produces = "application/json")
    @ResponseBody
    public String getBtnUrl(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Cookie stateCookie = CookieUtil.find(request, ReqParam.STATE);
            if (stateCookie == null)
                throw new MissedStateCookieException();
            String state = stateCookie.getValue();
            HashMap<String, String> urlMap = new HashMap<>();
            for (String providerName :
                    Arrays.asList(Provider.VK, Provider.YANDEX, Provider.GOOGLE, Provider.DISCORD, Provider.GIT)) {
                OauthProps props = propsFactory.getFor(providerName);
                UrlBuilder urlBuilder = new UrlBuilder(props.get(PropName.DOMAIN_AUTH));
                urlBuilder
                        .addParam(ReqParam.CLIENT_ID, props.get(PropName.CLIENT_ID))
                        .addParam(ReqParam.REDIRECT_URI, String.format("%s/oauth/%s",
                                TextQuestApplication.getRootUrl(), providerName))
                        .addParam(ReqParam.RESPONSE_TYPE, ReqParam.CODE)
                        .addParam(ReqParam.DISPLAY, "popup")
                        .addParam(ReqParam.STATE, state)
                        .addParam(ReqParam.SCOPE, props.get(PropName.SCOPE));
                String url = urlBuilder.build();
                urlMap.put(providerName, url);
            }
            return ObjectParser.parse(urlMap);
        } catch (OauthException e) {
            response.sendError(402);
            return null;
        }
    }

    private void attachTokens(HttpServletResponse res, User user) {
        Refresh refresh = new Refresh();
        refresh.setValue(RefreshUtil.parse());
        user.addToken(refresh);
        userService.update(user);
        RefreshUtil.attach(res, refresh);
        JwtUtil.attach(res, user);
    }
}
