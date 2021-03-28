package app.text_quest.controller.util.token;

import app.text_quest.controller.oauth.util.constant.SecureParam;
import app.text_quest.controller.util.CookieUtil;
import app.text_quest.controller.util.constant.Period;
import app.text_quest.database.model.user.User;
import app.text_quest.security.util.secretFactory.types.JwtKeyFactory;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

import javax.servlet.http.HttpServletResponse;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;


public abstract class JwtUtil {

    private final static JwtKeyFactory jwtKeyFactory = new JwtKeyFactory();

    private final static int PERIOD = Period.HOUR;
    private final static Key KEY = Keys.hmacShaKeyFor(jwtKeyFactory.create());

    public static String parse(User user) {
        HashMap<String, String> userMap = new HashMap<>();
        userMap.put("id", String.valueOf(user.getId()));
        userMap.put("name", user.getName());
        return Jwts.builder()
                .setClaims(userMap)
                .setExpiration(new Date(System.currentTimeMillis() + PERIOD * 1000L))
                .signWith(KEY)
                .compact();
    }

    public static User extract(String jwt) throws SignatureException, MalformedJwtException {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(KEY)
                .build().parseClaimsJws(jwt).getBody();
        User user = new User(Long.parseLong((String) claims.get("id")));
        user.setName((String) claims.get("name"));
        return user;
    }

    public static void attach(HttpServletResponse response, User user) {
        CookieUtil.add(
                response,
                SecureParam.JWT,
                parse(user),
                PERIOD);
    }
}
