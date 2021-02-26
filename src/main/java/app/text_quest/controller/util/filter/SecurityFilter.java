package app.text_quest.controller.util.filter;

import app.text_quest.security.Authentication;
import app.text_quest.util.LoggerFactory;
import app.text_quest.util.enums.LogType;
import org.apache.log4j.Logger;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
@Order(2)
public class SecurityFilter implements Filter {

    private static final Logger requestLogger = LoggerFactory.getLogger(LogType.REQUEST);
    private final Authentication authentication;

    public SecurityFilter(Authentication authentication) {
        this.authentication = authentication;
    }

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        chain.doFilter(request, response);
        // TODO: 26.02.2021


    }
}
