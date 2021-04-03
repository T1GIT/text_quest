package app.text_quest.controller;


import app.text_quest.controller.oauth.util.constant.SecureParam;
import app.text_quest.controller.util.CookieUtil;
import app.text_quest.controller.util.ObjectParser;
import app.text_quest.controller.util.constant.Period;
import app.text_quest.security.auth.Auth;
import app.text_quest.security.util.secretFactory.types.StateFactory;
import app.text_quest.util.LoggerFactory;
import app.text_quest.util.constant.LogType;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;


/**
 * A main controller.
 * <p>
 * After requesting root address user will receive
 * index html, that contains link for css file.
 * <p>
 * Html page contains div with id "root", that is used
 * as a React root for rendering.
 * <p>
 * Before running server, must build public files
 * with webpack using webpack config in the root directory
 * of the project.
 */
@Controller
@RequestMapping("/")
public class RootController {

    /**
     * Logger for recording exceptions
     */
    private final static Logger logger = LoggerFactory.getLogger(LogType.ERROR);
    /**
     * Factory for parsing state - string for identifying user for oauth service
     */
    private final static StateFactory stateFactory = new StateFactory();
    /**
     * Authorisation in the current request context
     */
    private final Auth auth;

    /**
     * Class constructor injecting auth context
     *
     * @param auth auth context
     */
    public RootController(Auth auth) {
        this.auth = auth;
    }

    /**
     * A root mapping for getting index page.
     *
     * @param model data for inserting into a html file
     * @param res   response to add cookie "state"
     * @return index page
     */
    @GetMapping("")
    public String root(Model model, HttpServletResponse res) {
        CookieUtil.add(res, SecureParam.STATE, stateFactory.create(), Period.DAY);
        model.addAttribute("isAuthorised", ObjectParser.parse(auth.isAuthenticated()));
        return "index.min";
    }

}
