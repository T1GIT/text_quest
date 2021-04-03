package app.text_quest.controller.util.token;

import app.text_quest.controller.oauth.util.constant.SecureParam;
import app.text_quest.controller.util.CookieUtil;
import app.text_quest.controller.util.constant.Period;
import app.text_quest.database.model.Refresh;
import app.text_quest.security.util.secretFactory.types.RefreshFactory;

import javax.servlet.http.HttpServletResponse;


/**
 * Provides static methods for parsing and attaching refresh tokens.
 */
public abstract class RefreshUtil {

    /**
     * Factory for producing refresh tokens
     */
    private final static RefreshFactory refreshFactory = new RefreshFactory();

    /**
     * Expire period of the refresh token cookie
     */
    private final static int PERIOD = Period.YEAR;

    /**
     * Creates refresh token using {@link RefreshFactory}
     *
     * @return created token
     */
    public static String parse() {
        return refreshFactory.create();
    }

    /**
     * Adds refresh token cookie to the response
     *
     * @param response target to add cookie
     * @param refresh  token to add
     */
    public static void attach(HttpServletResponse response, Refresh refresh) {
        CookieUtil.add(
                response,
                SecureParam.REFRESH,
                refresh.getValue(),
                PERIOD);
    }
}
