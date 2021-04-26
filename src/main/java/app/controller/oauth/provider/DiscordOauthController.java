package app.controller.oauth.provider;


import app.controller.oauth.OauthController;
import app.controller.oauth.util.constant.Provider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @see app.controller.oauth.OauthController
 */
@Controller
public class DiscordOauthController extends OauthController {

    public DiscordOauthController() {
        super(Provider.DISCORD);
    }

    @GetMapping("/discord")
    @Override
    public String oauthEndpoint(HttpServletRequest request, HttpServletResponse response) {
        return super.oauthEndpoint(request, response);
    }
}
