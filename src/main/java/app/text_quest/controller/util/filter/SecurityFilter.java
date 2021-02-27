package app.text_quest.controller.util.filter;

import app.text_quest.security.Authorisation;
import app.text_quest.util.LoggerFactory;
import app.text_quest.util.enums.LogType;
import org.apache.log4j.Logger;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
@Order(2)
public class SecurityFilter extends AbstractFilter {

    private static final Logger requestLogger = LoggerFactory.getLogger(LogType.REQUEST);

    public SecurityFilter(Authorisation authorisation) {
        super(authorisation, "^/$");
    }

    @Override
    protected void doAction(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        doRequest();
    }
}
