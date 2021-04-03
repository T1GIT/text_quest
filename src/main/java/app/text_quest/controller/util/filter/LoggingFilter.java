package app.text_quest.controller.util.filter;

import app.text_quest.TextQuestApplication;
import app.text_quest.util.LoggerFactory;
import app.text_quest.util.constant.LogType;
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
        chain.doFilter(request, response);
        String contentType;
        switch (response.getStatus()) {
            case 302:
                contentType = "redirect:" + response.getHeader("Location");
                break;
            case 404:
                contentType = "not found:redirect:" + TextQuestApplication.getRootUrl();
                break;
            default:
                contentType = response.getContentType();
                break;
        }
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
