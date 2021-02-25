package app.text_quest.controller;


import app.text_quest.util.LoggerFactory;
import app.text_quest.util.enums.LogType;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;


@Controller
public class RootController {

    private final Logger logger = LoggerFactory.getLogger(LogType.ERROR);

    @GetMapping("/")
    public String root(HttpServletRequest request) {
        try {
            return "index.min";
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null; // TODO: add error page
    }
}
