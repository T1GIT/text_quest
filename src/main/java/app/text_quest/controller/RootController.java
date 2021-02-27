package app.text_quest.controller;


import app.text_quest.controller.util.oauth.enums.Provider;
import app.text_quest.controller.util.oauth.enums.ReqParam;
import app.text_quest.controller.util.oauth.util.ObjectParser;
import app.text_quest.controller.util.oauth.util.request.BtnUrlParser;
import app.text_quest.security.Authentication;
import app.text_quest.util.LoggerFactory;
import app.text_quest.util.enums.LogType;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.UUID;


@Controller
public class RootController {

    private static final Logger logger = LoggerFactory.getLogger(LogType.ERROR);
    private final Authentication authentication;

    public RootController(Authentication authentication) {
        this.authentication = authentication;
    }

    @GetMapping("/")
    public String root(Model model, HttpServletRequest request, HttpServletResponse response) {
        try {
            oauthConfiguring(model, response);
            return "index.min";
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null; // TODO: add error page
    }

    private void oauthConfiguring(Model model, HttpServletResponse response) {
        String state = UUID.randomUUID().toString();
        response.addCookie(getStateCookie(state));
        model.addAttribute("btnHref", ObjectParser.parse(getBtnUrl(state)));
    }

    private HashMap<String, String> getBtnUrl(String state) {
        HashMap<String, String> urlMap = new HashMap<>();
        BtnUrlParser parser = new BtnUrlParser(state);
        for (Provider provider : Provider.values()) {
            urlMap.put(provider.name().toLowerCase(), parser.generateUrl(provider));
        }
        return urlMap;
    }

    private Cookie getStateCookie(String state) {
        Cookie cookie = new Cookie(ReqParam.STATE.name().toLowerCase(), state);
        cookie.setMaxAge(24 * 60 * 60);
        return cookie;
    }
}
