package app.text_quest.controller;


import app.text_quest.controller.oauth.util.BtnUrlParser;
import app.text_quest.controller.oauth.util.constant.Provider;
import app.text_quest.controller.oauth.util.constant.SecureParam;
import app.text_quest.controller.util.CookieUtil;
import app.text_quest.controller.util.ObjectParser;
import app.text_quest.controller.util.constant.Period;
import app.text_quest.security.auth.Auth;
import app.text_quest.security.util.secretFactory.types.StateFactory;
import app.text_quest.util.LoggerFactory;
import app.text_quest.util.constant.LogType;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;


@Controller
@RequestMapping("/")
public class RootController {

    private final static StateFactory stateFactory = new StateFactory();
    private final static Logger logger = LoggerFactory.getLogger(LogType.ERROR);
    private final Auth auth;

    public RootController(Auth auth) {
        this.auth = auth;
    }

    @GetMapping("")
    public String root(HttpServletRequest request, Model model, HttpServletResponse res) {
        try {

            injectData(res, model);

            return "index.min";
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null; // TODO: add error page
    }

    private void injectData(HttpServletResponse res, Model model) {
        String state = stateFactory.create();
        CookieUtil.add(res, SecureParam.STATE, state, Period.DAY);
        model.addAttribute("btnHref", ObjectParser.parse(getBtnUrl(state)));
        model.addAttribute("isAuthorised", ObjectParser.parse(auth.isAuthenticated()));
    }

    private HashMap<String, String> getBtnUrl(String state) {
        HashMap<String, String> urlMap = new HashMap<>();
        for (String provider : Arrays.asList(Provider.VK, Provider.YANDEX, Provider.GOOGLE, Provider.DISCORD, Provider.GIT)) {
            urlMap.put(provider, BtnUrlParser.generateUrl(state, provider));
        }
        return urlMap;
    }
}
