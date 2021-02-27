package app.text_quest.controller.util.filter;

import app.text_quest.security.Authorisation;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;


public abstract class AbstractFilter implements Filter {

    protected final Authorisation authorisation;
    private final Pattern pattern;
    private FilterChain chain;
    private ServletRequest request;
    private ServletResponse response;

    protected AbstractFilter(Authorisation authorisation, String regExp) {
        this.pattern = Pattern.compile(regExp);
        this.authorisation = authorisation;
    }

    public final void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        this.request = request;
        this.response = response;
        this.chain = chain;

        if (pattern.matcher(req.getRequestURI()).matches()) {
            doAction(req, res);
        } else {
            doRequest();
        }
    }

    protected abstract void doAction(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException;

    protected void doRequest() throws IOException, ServletException {
        chain.doFilter(request, response);
    }

}
