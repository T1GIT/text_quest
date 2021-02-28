package app.text_quest.controller;


import app.text_quest.database.repo.BasicUserRepository;
import app.text_quest.util.LoggerFactory;
import app.text_quest.util.enums.LogType;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/authorisation")
public class AuthorisationController {

    private final Logger logger = LoggerFactory.getLogger(LogType.ERROR);
    private final BasicUserRepository repository;

    public AuthorisationController(BasicUserRepository repository) {
        this.repository = repository;
    }

    @RequestMapping("/oauth")
    public String oauthAuthorisation() {
        return "fefefaejifijef";
    }
}
