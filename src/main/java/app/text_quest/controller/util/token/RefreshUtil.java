package app.text_quest.controller.util.token;

import app.text_quest.controller.oauth.util.constant.SecureParam;
import app.text_quest.controller.util.CookieUtil;
import app.text_quest.controller.util.constant.Period;
import app.text_quest.database.model.Refresh;
import app.text_quest.security.util.secretFactory.types.RefreshFactory;

import javax.servlet.http.HttpServletResponse;


public abstract class RefreshUtil {

    private final static RefreshFactory refreshFactory = new RefreshFactory();

    private final static int PERIOD = Period.YEAR;

    public static String parse() {
        return refreshFactory.create();
    }

    public static void attach(HttpServletResponse response, Refresh refresh) {
        CookieUtil.add(
                response,
                SecureParam.REFRESH,
                refresh.getValue(),
                PERIOD);
    }
}
