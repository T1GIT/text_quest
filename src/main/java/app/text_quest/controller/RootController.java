package app.text_quest.controller;


import app.text_quest.util.FileLogger;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class RootController {

    private final Logger logger = Logger.getLogger("controllerLog");

    @RequestMapping("/")
    @ResponseBody
    public String root() {
        try {
            return "index.min";
        } catch (Exception e) {
            logger.error(FileLogger.getExceptionLog(e));
        }
        return null; // TODO: 19.02.2021 Do smth with errors
    }
}
