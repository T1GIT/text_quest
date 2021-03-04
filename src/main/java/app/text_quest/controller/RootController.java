package app.text_quest.controller;


import app.text_quest.controller.oauth.util.enums.Provider;
import app.text_quest.controller.oauth.util.enums.SecureParam;
import app.text_quest.controller.oauth.util.request.BtnUrlParser;
import app.text_quest.controller.util.ObjectParser;
import app.text_quest.security.auth.Auth;
import app.text_quest.security.util.secretFactory.types.StateFactory;
import app.text_quest.util.LoggerFactory;
import app.text_quest.util.enums.LogType;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    public String root(HttpServletRequest request, Model model, HttpServletResponse response) {
        try {

            injectData(request, model);

            return "index.min";
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null; // TODO: add error page
    }

    private void injectData(HttpServletRequest request, Model model) {
        String state = stateFactory.create();
        request.getSession().setAttribute(SecureParam.STATE.name(), state);
        model.addAttribute("btnHref", ObjectParser.parse(getBtnUrl(state)));
        model.addAttribute("isAuthorised", ObjectParser.parse(auth.isAuthenticated()));
    }

    private HashMap<String, String> getBtnUrl(String state) {
        HashMap<String, String> urlMap = new HashMap<>();
        BtnUrlParser parser = new BtnUrlParser(state);
        for (Provider provider : Provider.values()) {
            urlMap.put(provider.name().toLowerCase(), parser.generateUrl(provider));
        }
        return urlMap;
    }
}
