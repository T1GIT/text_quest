package app.controller.filter;

import app.controller.oauth.util.constant.SecureParam;
import app.controller.util.CookieUtil;
import app.controller.util.exception.missedToken.types.MissedJwtException;
import app.controller.util.exception.missedToken.types.MissedRefreshException;
import app.controller.util.token.JwtUtil;
import app.controller.util.token.RefreshUtil;
import app.database.model.Refresh;
import app.database.model.user.User;
import app.database.service.RefreshService;
import app.security.auth.Auth;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Filter for validating requests and filling authorisation context.
 */
@Component
@Order(2)
public class SecurityFilter extends AbstractFilter {

    /**
     * Service for interaction with refresh tokens in the database
     */
    private final RefreshService refreshService;

    /**
     * Authorisation context
     */
    private final Auth auth;

    public SecurityFilter(Auth auth, RefreshService refreshService) {
        super("^/(game|log)/.*$");
        this.auth = auth;
        this.refreshService = refreshService;
    }

    /**
     * Filter body.
     * Checks if JWT cookie exists, in this case validates it, if it's valid, then parses user and fill auth context.
     * If JWT cookie doesn't exist or JWT is invalid, then checks refresh token.
     * If refresh token cookie exists and the same token is found in the database, parses JWT and attaches it to the
     * cookie. Otherwise cleans tokens in the cookie and answers with error code 401.
     *
     * @param request  contains cookie
     * @param response for attaching tokens and returning error
     * @param chain    other filters
     * @throws IOException      if error with input output occurs
     * @throws ServletException if error when {@code chain.doFilter()} occurs
     */
    @Override
    public void doAction(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            System.out.println("try");
            auth.setUser(parseUser(request, response));
            chain.doFilter(request, response);
        } catch (MissedRefreshException e) {
            CookieUtil.remove(response, SecureParam.REFRESH);
            CookieUtil.remove(response, SecureParam.JWT);
            if (request.getRequestURI().equals("/")) {
                chain.doFilter(request, response);
            } else {
                response.sendError(401, "Non authorised access");
            }
        }
    }

    /**
     * Parses user from the JWT. Checks if JWT cookie exists, in this case validates it, if it's valid, then parses user and fill auth context.
     * If JWT cookie doesn't exist or JWT is invalid, then checks refresh token.
     * If refresh token cookie exists and the same token is found in the database, parses JWT and attaches it to the
     * cookie. Otherwise throws {@link MissedRefreshException}.
     *
     * @param request     contains cookie
     * @param response    for attaching tokens and returning error
     * @return parsed {@link User} object
     * @throws MissedRefreshException if cookie doesn't contain refresh token or it wasn't found in the database
     */
    protected User parseUser(HttpServletRequest request, HttpServletResponse response) throws MissedRefreshException {
        User user;
        try {
            String jwtCookie = CookieUtil.get(request, SecureParam.JWT);
            System.out.println(jwtCookie);
            if (jwtCookie == null)
                throw new MissedJwtException();
            user = JwtUtil.extract(jwtCookie);
            System.out.println(user);
        } catch (MissedJwtException | SignatureException | MalformedJwtException e) {
            String refreshCookie = CookieUtil.get(request, SecureParam.REFRESH);
            System.out.println(refreshCookie);
            if (refreshCookie == null)
                throw new MissedRefreshException();
            Refresh refresh = refreshService.getByValue(refreshCookie);
            System.out.println(refresh);
            if (refresh == null)
                throw new MissedRefreshException();
            updateTokens(response, refresh);
            user = refresh.getUser();
        }
        return user;
    }

    /**
     * Change refresh token value and adds cookies to the response, including:
     * <ul>
     * <li> JWT
     * <li> Refresh token
     * </ul>
     *
     * @param response target for attaching tokens
     * @param refresh  refresh token object
     */
    protected void updateTokens(HttpServletResponse response, Refresh refresh) {
        refresh.setValue(RefreshUtil.parse());
        refreshService.update(refresh);
        RefreshUtil.attach(response, refresh);
        JwtUtil.attach(response, refresh.getUser());
    }
}
