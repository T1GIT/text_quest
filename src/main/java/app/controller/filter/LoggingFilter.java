package app.controller.filter;

import app.TextQuestApplication;
import app.util.LoggerFactory;
import app.util.constant.LogType;
import org.apache.log4j.Logger;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;


/**
 * Filter for logging requests
 */
@Component
@Order(1)
public class LoggingFilter extends AbstractFilter {

    /**
     * Logger for recording requests
     */
    private static final Logger requestLogger = LoggerFactory.getLogger(LogType.REQUEST);

    /**
     * Class constructor.
     * Includes all requests into this filter.
     */
    protected LoggingFilter() {
        super(".*");
    }

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
    protected void doAction(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        long startTime = new Date().getTime();
        System.out.println("log");
        chain.doFilter(request, response);
        String contentType = switch (response.getStatus()) {
            case 302 -> "redirect:" + response.getHeader("Location");
            case 404 -> "not found:redirect:" + TextQuestApplication.getRootUrl();
            default -> response.getContentType();
        };
        requestLogger.info(String.format("%3d %-6s %-30s %4d ms   %s",
                response.getStatus(),
                request.getMethod(),
                request.getRequestURI(),
                new Date().getTime() - startTime,
                contentType
                )
        );
    }
}
