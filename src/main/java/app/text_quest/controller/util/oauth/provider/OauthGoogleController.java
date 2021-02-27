package app.text_quest.controller.util.oauth.provider;


import app.text_quest.controller.util.oauth.enums.Provider;
import app.text_quest.controller.util.oauth.util.OauthController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


@Controller
public class OauthGoogleController extends OauthController {

    public OauthGoogleController() {
        super(Provider.GOOGLE);
    }

    @GetMapping("oauth/google")
    @Override
    public String oauthEndpoint(@CookieValue(value = "state") Cookie cookieState, HttpServletRequest request) {
        return super.oauthEndpoint(cookieState, request);
    }
}
