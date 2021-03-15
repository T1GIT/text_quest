package app.text_quest.controller;


import app.text_quest.controller.oauth.util.constant.SecureParam;
import app.text_quest.controller.util.constant.Status;
import app.text_quest.controller.util.json.auth.JsonAnswer;
import app.text_quest.controller.util.json.auth.JsonForm;
import app.text_quest.controller.util.token.JwtUtil;
import app.text_quest.controller.util.token.RefreshUtil;
import app.text_quest.database.model.Refresh;
import app.text_quest.database.model.Setting;
import app.text_quest.database.model.user.User;
import app.text_quest.database.model.user.types.BasicUser;
import app.text_quest.database.model.user.types.OauthUser;
import app.text_quest.database.service.BasicUserService;
import app.text_quest.database.service.OauthUserService;
import app.text_quest.database.service.UserService;
import app.text_quest.security.Hash;
import app.text_quest.security.util.Validator;
import app.text_quest.util.LoggerFactory;
import app.text_quest.util.constant.LogType;
import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("/auth")
public class AuthController {

    private final static Logger logger = LoggerFactory.getLogger(LogType.ERROR);
    private final static Gson gson = new Gson();
    private final BasicUserService basicService;
    private final OauthUserService oauthService;
    private final UserService userService;

    public AuthController(BasicUserService basicService, OauthUserService oauthService, UserService userService) {
        this.basicService = basicService;
        this.oauthService = oauthService;
        this.userService = userService;
    }

    @PostMapping(path = "/login", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public String login(@RequestBody JsonForm jsonForm, HttpServletResponse response) {
        JsonAnswer jsonAnswer = new JsonAnswer();
        BasicUser user = basicService.getByMail(jsonForm.getMail());
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
        BasicUser user = basicService.getByMail(jsonForm.getMail());
        if (user != null) {
            jsonAnswer.setMsg(Status.EXISTS);
        } else if (!Validator.mail(jsonForm.getMail())) {
            jsonAnswer.setMsg(Status.BAD_EMAIL);
        } else if (!Validator.psw(jsonForm.getPsw())) {
            jsonAnswer.setMsg(Status.BAD_PSW);
        } else {
            user = new BasicUser();
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
            System.out.println(user);
        }
        attachTokens(response, user);
        return "redirect:/";
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
