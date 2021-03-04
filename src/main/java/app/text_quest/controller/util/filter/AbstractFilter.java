package app.text_quest.controller.util.filter;

import app.text_quest.security.auth.Auth;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;


public abstract class AbstractFilter implements Filter {

    protected final Auth auth;
    private final Pattern include;
    private Pattern exclude;

    protected AbstractFilter(Auth auth, String include) {
        this.include = Pattern.compile(include);
        this.auth = auth;
    }

    protected AbstractFilter(Auth auth, String include, String exclude) {
        this.include = Pattern.compile(include);
        this.exclude = Pattern.compile(exclude);
        this.auth = auth;
    }

    public final void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String url = req.getRequestURI();
        if (include.matcher(url).matches() && (exclude == null || !exclude.matcher(url).matches())) {
            doAction(req, res, chain);
        } else {
            chain.doFilter(req, res);
        }
    }

    protected abstract void doAction(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException;
}
