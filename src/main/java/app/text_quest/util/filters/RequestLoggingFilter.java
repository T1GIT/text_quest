package app.text_quest.util.filters;

import org.apache.log4j.Logger;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;


@Component
@Order(2)
public class RequestLoggingFilter implements Filter {


    private static final Logger logger = Logger.getLogger(RequestLoggingFilter.class);

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        long startTime = new Date().getTime();

        chain.doFilter(request, response);

        logger.info(String.format("%3d %-6s %-30s [%d ms] %s",
                res.getStatus(),
                req.getMethod(),
                req.getRequestURI(),
                new Date().getTime() - startTime,
                res.getContentType()
                )
        );
    }
}
