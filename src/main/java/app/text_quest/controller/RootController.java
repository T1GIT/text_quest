package app.text_quest.controller;


import app.text_quest.util.ExceptionLogger;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class RootController {

    private final Logger logger = Logger.getLogger("controllerLogger");

    @GetMapping("/")
    public String root() {
        try {
            return "index.min";
        } catch (Exception e) {
            logger.error(ExceptionLogger.getExceptionLog(e));
        }
        return null; // TODO: add error page
    }
}
