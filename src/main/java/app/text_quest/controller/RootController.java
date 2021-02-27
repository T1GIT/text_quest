package app.text_quest.controller;


import app.text_quest.controller.util.oauth.enums.Provider;
import app.text_quest.controller.util.oauth.util.ObjectParser;
import app.text_quest.controller.util.oauth.util.request.BtnUrlParser;
import app.text_quest.security.Authorisation;
import app.text_quest.util.LoggerFactory;
import app.text_quest.util.enums.LogType;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;


@Controller
public class RootController {

    private static final Logger logger = LoggerFactory.getLogger(LogType.ERROR);
    private final Authorisation authorisation;

    public RootController(Authorisation authorisation) {
        this.authorisation = authorisation;
    }


    @GetMapping("/")
    public String root() {
        return "redirect:/start";
    }

    @GetMapping("/start")
    public String start(Model model, HttpServletRequest request) {
        try {
            model.addAttribute("btnHref", ObjectParser.parse(getBtnUrl()));
            return "index.min";
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null; // TODO: add error page
    }

    private HashMap<String, String> getBtnUrl() {
        HashMap<String, String> urlMap = new HashMap<>();
        for (Provider provider : Provider.values()) {
            urlMap.put(provider.name().toLowerCase(), BtnUrlParser.getUrl(provider));
        }
        return urlMap;
    }
}
