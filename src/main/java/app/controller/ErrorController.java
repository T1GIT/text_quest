package app.controller;

import app.controller.oauth.util.constant.ReqParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/error")
public class ErrorController {

    @GetMapping("/not_found")
    public String notFound(HttpServletRequest request) {
        request.setAttribute(ReqParam.ERROR, "Page not found");
        return "redirect:/";
    }

    @GetMapping("/unauthorised")
    public String unauthorised(HttpServletRequest request) {
        request.setAttribute(ReqParam.ERROR, "Unauthorised access");
        return "redirect:/";
    }
}
