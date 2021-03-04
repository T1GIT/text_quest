package app.text_quest.controller.oauth.provider;


import app.text_quest.controller.oauth.OauthController;
import app.text_quest.controller.oauth.util.enums.Provider;
import app.text_quest.security.auth.OauthAuthManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
public class OauthYandexController extends OauthController {

    public OauthYandexController(OauthAuthManager oauthAuthManager) {
        super(oauthAuthManager, Provider.YANDEX);
    }

    @GetMapping("/yandex")
    @Override
    public String oauthEndpoint(HttpServletRequest request, HttpServletResponse response) {
        return super.oauthEndpoint(request, response);
    }
}
