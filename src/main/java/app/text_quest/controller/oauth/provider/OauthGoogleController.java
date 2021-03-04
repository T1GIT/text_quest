package app.text_quest.controller.oauth.provider;


import app.text_quest.controller.oauth.OauthController;
import app.text_quest.controller.oauth.util.enums.Provider;
import app.text_quest.security.auth.OauthAuthManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
public class OauthGoogleController extends OauthController {

    public OauthGoogleController(OauthAuthManager oauthAuthManager) {
        super(oauthAuthManager, Provider.GOOGLE);
    }

    @GetMapping("/google")
    @Override
    public String oauthEndpoint(HttpServletRequest request, HttpServletResponse response) {
        return super.oauthEndpoint(request, response);
    }
}
