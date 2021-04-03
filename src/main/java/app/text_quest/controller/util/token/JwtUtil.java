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


/**
 * Provides static methods for creating and attaching JWT tokens.
 */
public abstract class JwtUtil {

    /**
     * Factory for producing JWT keys
     */
    private final static JwtKeyFactory jwtKeyFactory = new JwtKeyFactory();

    /**
     * Expires period of the JWT cookie
     */
    private final static int PERIOD = Period.HOUR;

    /**
     * Hmac SHA key, parsed from the key, produce by the {@link JwtKeyFactory}
     */
    private final static Key KEY = Keys.hmacShaKeyFor(jwtKeyFactory.create());

    /**
     * Creates the JWT from the user object.
     * Includes id and name of the user.
     *
     * @param user target to parse
     * @return parsed JWT
     */
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

    /**
     * Parses {@link User} object with id and name from the JWT
     *
     * @param jwt input JWT
     * @return parsed user
     * @throws SignatureException    if signature is invalid
     * @throws MalformedJwtException if JWT is broken
     */
    public static User extract(String jwt) throws SignatureException, MalformedJwtException {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(KEY)
                .build().parseClaimsJws(jwt).getBody();
        User user = new User(Long.parseLong((String) claims.get("id")));
        user.setName((String) claims.get("name"));
        return user;
    }


    /**
     * Adds refresh token cookie to the response
     *
     * @param response target to add cookie
     * @param user     object to include into JWT
     */
    public static void attach(HttpServletResponse response, User user) {
        CookieUtil.add(
                response,
                SecureParam.JWT,
                parse(user),
                PERIOD);
    }
}
