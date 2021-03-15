package app.text_quest.controller.util.filter;

import app.text_quest.controller.oauth.util.constant.SecureParam;
import app.text_quest.controller.util.CookieUtil;
import app.text_quest.controller.util.exception.missedToken.types.MissedJwtException;
import app.text_quest.controller.util.exception.missedToken.types.MissedRefreshException;
import app.text_quest.controller.util.token.JwtUtil;
import app.text_quest.controller.util.token.RefreshUtil;
import app.text_quest.database.model.Refresh;
import app.text_quest.database.model.user.User;
import app.text_quest.database.service.RefreshService;
import app.text_quest.security.auth.Auth;
import app.text_quest.security.util.secretFactory.types.RefreshFactory;
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


@Component
@Order(2)
public class SecurityFilter extends AbstractFilter {

    private final static RefreshFactory REFRESH_FACTORY = new RefreshFactory();

    private final RefreshService refreshService;

    public SecurityFilter(Auth auth, RefreshService refreshService) {
        super(auth, "^.*$", "^/(build|oauth|auth|log)/.*$");
        this.refreshService = refreshService;
    }

    @Override
    public void doAction(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            Cookie tokenCookie = CookieUtil.find(request, SecureParam.REFRESH);
            if (tokenCookie == null) throw new MissedRefreshException();
            auth.setUser(parseUser(request, response, tokenCookie.getValue()));
            chain.doFilter(request, response);
        } catch (MissedRefreshException e) {
            if (!request.getRequestURI().equals("/")) {
                initAccessError(response);
            } else {
                chain.doFilter(request, response);
            }
        }
    }

    protected User parseUser(HttpServletRequest request, HttpServletResponse response, String tokenString) throws MissedRefreshException {
        User user;
        try {
            Cookie jwtCookie = CookieUtil.find(request, SecureParam.JWT);
            if (jwtCookie == null) {
                throw new MissedJwtException();
            }
            user = JwtUtil.extract(jwtCookie.getValue());
        } catch (MissedJwtException | SignatureException | MalformedJwtException e) {
            Refresh refresh = refreshService.getByValue(tokenString);
            if (refresh == null) throw new MissedRefreshException();
            updateTokens(response, refresh);
            user = refresh.getUser();
        }
        return user;
    }

    protected void initAccessError(HttpServletResponse response) throws IOException {
        CookieUtil.remove(response, SecureParam.REFRESH);
        CookieUtil.remove(response, SecureParam.JWT);
        response.sendError(401, "Non authorised access");
    }

    protected void updateTokens(HttpServletResponse response, Refresh refresh) {
        refresh.setValue(REFRESH_FACTORY.create());
        refreshService.update(refresh);
        RefreshUtil.attach(response, refresh);
        JwtUtil.attach(response, refresh.getUser());
    }
}
