package app.text_quest.controller.util.filter;

import app.text_quest.TextQuestApplication;
import app.text_quest.security.auth.Auth;
import app.text_quest.util.LoggerFactory;
import app.text_quest.util.enums.LogType;
import org.apache.log4j.Logger;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;


@Component
@Order(1)
public class LoggingFilter extends AbstractFilter {

    private static final Logger requestLogger = LoggerFactory.getLogger(LogType.REQUEST);

    protected LoggingFilter(Auth auth) {
        super(auth, ".*");
    }

    @Override
    protected void doAction(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        long startTime = new Date().getTime();
        chain.doFilter(request, response);
        String content = switch (response.getStatus()) {
            case 302 -> "redirect:" + response.getHeader("Location");
            case 404 -> "not found:redirect:" + TextQuestApplication.getRootUrl();
            default -> response.getContentType();
        };
        requestLogger.info(String.format("%3d %-6s %-30s %4d ms   %s",
                response.getStatus(),
                request.getMethod(),
                request.getRequestURI(),
                new Date().getTime() - startTime,
                content
                )
        );
    }
}
