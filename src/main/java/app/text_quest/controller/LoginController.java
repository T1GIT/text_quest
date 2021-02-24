package app.text_quest.controller;


import app.text_quest.util.LoggerFactory;
import app.text_quest.util.enums.LogType;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/login")
public class LoginController {

    private final Logger logger = LoggerFactory.getLogger(LogType.ERROR);

    @ResponseBody
    @PostMapping("/test")
    public String auth(@RequestParam int key1, @RequestParam int key2) {
        System.out.println(key1 + key2);
        return "true";
    }
}
