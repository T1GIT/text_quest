package app.text_quest.controller.util.filter;

import app.text_quest.controller.oauth.util.enums.SecureParam;
import app.text_quest.controller.util.CookieUtil;
import app.text_quest.controller.util.JwtUtil;
import app.text_quest.controller.util.enums.Period;
import app.text_quest.database.model.Token;
import app.text_quest.database.model.user.User;
import app.text_quest.database.service.TokenService;
import app.text_quest.database.service.UserService;
import app.text_quest.security.auth.Auth;
import app.text_quest.security.util.secretFactory.types.TokenFactory;
import app.text_quest.util.exceptions.MissedJwtException;
import app.text_quest.util.exceptions.MissedTokenException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
@Order(2)
public class SecurityFilter extends AbstractFilter {

    private final static TokenFactory tokenFactory = new TokenFactory();

    private final TokenService tokenService;
    private final UserService userService;

    public SecurityFilter(Auth auth, TokenService tokenService, UserService userService) {
        super(auth, "^/|(/game/.*)$");
        this.tokenService = tokenService;
        this.userService = userService;
    }

    @Override
    public void doAction(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        if (req.getRequestURI().equals("/")) {
            User user = (User) req.getSession().getAttribute(SecureParam.NEEDS_TOKENS.name());
            if (user != null) initAuth(req, res, user);
        } else {
            try {
                Cookie tokenCookie = CookieUtil.find(req, SecureParam.TOKEN.name());
                if (tokenCookie == null) throw new MissedTokenException();
                auth.setUser(parseUser(req, res, tokenCookie.getValue()));
            } catch (MissedTokenException e) {
                initAccessError(res);
            }
        }
        chain.doFilter(req, res);
    }

    @Transactional
    protected void initAuth(HttpServletRequest req, HttpServletResponse res, User user) {
        Token token = new Token();
        token.setValue(tokenFactory.create());
        user.addToken(token);
        userService.editUser(user);
        attachToken(res, token);
        attachJwt(res, user);
        req.getSession().removeAttribute(SecureParam.NEEDS_TOKENS.name());
    }

    protected User parseUser(HttpServletRequest req, HttpServletResponse res, String tokenString) throws MissedTokenException {
        User user;
        try {
            Cookie jwtCookie = CookieUtil.find(req, SecureParam.JWT.name());
            if (jwtCookie == null) throw new MissedJwtException();
            user = JwtUtil.parseUser(jwtCookie.getValue());
            auth.setUser(user);
        } catch (MissedJwtException | SignatureException | MalformedJwtException e) {
            Token token = tokenService.getByValue(tokenString);
            if (token == null) throw new MissedTokenException();
            user = token.getUser();
            refreshTokens(res, token);
        }
        return user;
    }

    protected void initAccessError(HttpServletResponse res) throws IOException {
        CookieUtil.remove(res, SecureParam.TOKEN.name());
        CookieUtil.remove(res, SecureParam.JWT.name());
        res.sendError(501);
    }

    protected void refreshTokens(HttpServletResponse response, Token token) {
        token.setValue(tokenFactory.create());
        tokenService.editToken(token);
        attachToken(response, token);
        attachJwt(response, token.getUser());
    }

    private void attachJwt(HttpServletResponse response, User user) {
        response.addCookie(CookieUtil.create(
                SecureParam.JWT.name(),
                JwtUtil.parseJwt(user),
                JwtUtil.getPeriod()));
    }

    private void attachToken(HttpServletResponse response, Token token) {
        response.addCookie(CookieUtil.create(
                SecureParam.TOKEN.name(),
                token.getValue(),
                Period.YEAR));
    }
}
