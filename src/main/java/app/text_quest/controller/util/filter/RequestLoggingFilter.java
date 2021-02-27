package app.text_quest.controller.util.filter;

import app.text_quest.security.Authentication;
import app.text_quest.util.LoggerFactory;
import app.text_quest.util.enums.LogType;
import org.apache.log4j.Logger;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;


@Component
@Order(1)
public class RequestLoggingFilter extends AbstractFilter {

    private static final Logger requestLogger = LoggerFactory.getLogger(LogType.REQUEST);

    protected RequestLoggingFilter(Authentication authentication) {
        super(authentication, ".*");
    }

    @Override
    protected void doAction(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        long startTime = new Date().getTime();
        doRequest();
        requestLogger.info(String.format("%3d %-6s %-30s %4d ms   %s",
                res.getStatus(),
                req.getMethod(),
                req.getRequestURI(),
                new Date().getTime() - startTime,
                res.getContentType()
                )
        );
    }
}
