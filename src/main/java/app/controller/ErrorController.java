package app.controller;

import app.controller.oauth.util.constant.ReqParam;
import app.controller.oauth.util.constant.SecureParam;
import app.controller.util.CookieUtil;
import app.controller.util.constant.Period;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/error")
public class ErrorController {

    @GetMapping("/not_found")
    public String notFound(HttpServletResponse response) {
        return redirectError(response,
                "Page not found");
    }

    @GetMapping("/unauthorised")
    public String unauthorised(HttpServletResponse response) {
        return redirectError(response,
                "Unauthorised access");
    }

    private static String redirectError(HttpServletResponse response, String error) {
        CookieUtil.add(response, ReqParam.ERROR, error, Period.YEAR);
        return "redirect:/";
    }
}
