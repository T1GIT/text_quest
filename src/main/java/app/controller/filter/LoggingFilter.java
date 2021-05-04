package app.controller.filter;

import app.TextQuestApplication;
import app.util.LoggerFactory;
import app.util.constant.LogType;
import org.apache.log4j.Logger;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;


/**
 * Filter for logging requests
 *
 * Parses and records log string from the request in the template:
 * <p>
 * [metadata...] [status code] [method] [url] [executing time]
 */
public class LoggingFilter implements Filter {

    /**
     * Logger for recording requests
     */
    private static final Logger requestLogger = LoggerFactory.getLogger(LogType.REQUEST);

    /**
     * Parses and records log string from the request in the template:
     * <p>
     * [metadata...] [status code] [method] [url] [executing time]
     *
     * @param request  for getting method and url
     * @param response for getting status code
     * @param chain    other filters
     * @throws IOException      if error with input output occurs
     * @throws ServletException if error when {@code chain.doFilter()} occurs
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        long startTime = new Date().getTime();
        chain.doFilter(request, response);
        String contentType = switch (res.getStatus()) {
            case 302 -> "redirect:" + res.getHeader("Location");
            case 404 -> "not found:redirect:" + TextQuestApplication.getRootUrl();
            default -> res.getContentType();
        };
        requestLogger.info(String.format("%d %-6s %-30s %4d ms   %s",
                res.getStatus(),
                req.getMethod(),
                req.getRequestURI(),
                new Date().getTime() - startTime,
                contentType
                )
        );
    }
}
