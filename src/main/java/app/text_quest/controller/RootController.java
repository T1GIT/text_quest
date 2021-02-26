package app.text_quest.controller;


import app.text_quest.controller.util.oauth.enums.OauthProvider;
import app.text_quest.controller.util.oauth.util.OauthController;
import app.text_quest.controller.util.oauth.util.ObjectParser;
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

    private final Logger logger = LoggerFactory.getLogger(LogType.ERROR);

    @GetMapping("/")
    public String root(Model model, HttpServletRequest request) {
        try {
            HashMap<String, String> btnHref = new HashMap<>();
            for (OauthProvider provider : OauthProvider.values()) {
                btnHref.put(provider.name().toLowerCase(), OauthController.getBtnUrl(provider));
            }
            model.addAttribute("btnHref", ObjectParser.parse(btnHref));
            return "index.min";
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null; // TODO: add error page
    }
}
