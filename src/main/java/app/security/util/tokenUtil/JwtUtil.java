package app.security.util.tokenUtil;

import app.controller.oauth.util.constant.SecureParam;
import app.controller.util.CookieUtil;
import app.security.util.constant.JwtClaims;
import app.controller.util.constant.Period;
import app.database.model.user.User;
import app.security.secretFactory.types.JwtKeyFactory;
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
    private final static long PERIOD = Period.HOUR;

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
        userMap.put(JwtClaims.ID, String.valueOf(user.getId()));
        userMap.put(JwtClaims.NAME, user.getName());
        userMap.put(JwtClaims.SOCKET_ID, user.getSocketId());
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
        User user = new User();
        user.setId(Long.parseLong((String) claims.get(JwtClaims.ID)));
        user.setName((String) claims.get(JwtClaims.NAME));
        user.setSocketId((String) claims.get(JwtClaims.SOCKET_ID));
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
