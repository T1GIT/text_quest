package app.controller;


import app.controller.oauth.util.constant.ReqParam;
import app.controller.oauth.util.constant.SecureParam;
import app.controller.oauth.util.exception.types.MissedStateCookieException;
import app.controller.util.CookieUtil;
import app.controller.util.ObjectParser;
import app.controller.util.constant.Period;
import app.security.auth.Auth;
import app.security.util.secretFactory.types.StateFactory;
import app.util.LoggerFactory;
import app.util.constant.LogType;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
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
     * @param model    data for inserting into a html file
     * @param response response to add cookie "state"
     * @return index page
     */
    @GetMapping("")
    public String root(Model model, HttpServletRequest request, HttpServletResponse response) {
        String errorCookie = CookieUtil.get(request, ReqParam.ERROR);
        CookieUtil.add(response, ReqParam.ERROR, null, Period.YEAR);
        model.addAttribute("isAuthorised", ObjectParser.parse(auth.isAuthenticated()));
        model.addAttribute("error", ObjectParser.parse(errorCookie));
        return "index.min";
    }

}
