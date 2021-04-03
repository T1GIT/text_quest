package app.text_quest.controller;


import app.text_quest.util.LoggerFactory;
import app.text_quest.util.constant.LogType;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

// TODO: Document
@Controller
@RequestMapping("/game")
public class GameController {

    private final Logger logger = LoggerFactory.getLogger(LogType.ERROR);

    @GetMapping("/")
    @ResponseBody
    public String a() {
        return "game";
    }


    // TODO: 01.03.2021 Don't forget erase JWT when set user name
    @PostMapping("/test")
    @ResponseBody
    public String auth(@RequestParam int key1, @RequestParam int key2) {
        System.out.println(key1 + key2);
        return "true";
    }
}
