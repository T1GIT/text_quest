package app.text_quest.controller.util.token;

import app.text_quest.controller.oauth.util.enums.SecureParam;
import app.text_quest.controller.util.CookieUtil;
import app.text_quest.controller.util.enums.Period;
import app.text_quest.database.model.Refresh;
import app.text_quest.security.util.secretFactory.types.RefreshFactory;

import javax.servlet.http.HttpServletResponse;


public abstract class RefreshUtil {

    private final static RefreshFactory refreshFactory = new RefreshFactory();

    private final static Period PERIOD = Period.YEAR;

    public static Period getPeriod() {
        return PERIOD;
    }

    public static String parse() {
        return refreshFactory.create();
    }

    public static void attach(HttpServletResponse response, Refresh refresh) {
        CookieUtil.add(
                response,
                SecureParam.REFRESH.name(),
                refresh.getValue(),
                PERIOD);
    }
}
