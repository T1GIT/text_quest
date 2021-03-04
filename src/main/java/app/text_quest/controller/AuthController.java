package app.text_quest.controller;


import app.text_quest.controller.oauth.util.enums.SecureParam;
import app.text_quest.controller.util.token.JwtUtil;
import app.text_quest.controller.util.token.RefreshUtil;
import app.text_quest.database.model.Refresh;
import app.text_quest.database.model.Setting;
import app.text_quest.database.model.user.User;
import app.text_quest.database.model.user.types.OauthUser;
import app.text_quest.database.service.BasicUserService;
import app.text_quest.database.service.OauthUserService;
import app.text_quest.database.service.UserService;
import app.text_quest.util.LoggerFactory;
import app.text_quest.util.enums.LogType;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("/auth")
public class AuthController {

    private final Logger logger = LoggerFactory.getLogger(LogType.ERROR);
    private final BasicUserService basicService;
    private final OauthUserService oauthService;
    private final UserService userService;

    public AuthController(BasicUserService basicService, OauthUserService oauthService, UserService userService) {
        this.basicService = basicService;
        this.oauthService = oauthService;
        this.userService = userService;
    }

    @GetMapping("/oauth")
    public String oauth(HttpServletRequest request, HttpServletResponse response) {
        String oauthId = (String) request.getAttribute(SecureParam.OAUTH_ID.name());
        OauthUser user = oauthService.getByOauthId(oauthId);
        if (user == null) {
            user = new OauthUser();
            user.setOauthId(oauthId);
            user.setSetting(new Setting());
            oauthService.add(user);
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
