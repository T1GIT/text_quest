package app.text_quest.controller;


import app.text_quest.database.repo.BasicUserRepository;
import app.text_quest.util.LoggerFactory;
import app.text_quest.util.enums.LogType;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/authorisation")
public class AuthController {

    private final Logger logger = LoggerFactory.getLogger(LogType.ERROR);
    private final BasicUserRepository repository;

    public AuthController(BasicUserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/oauth")
    public String authorise() {
        return "redirect:/";
    }
}
