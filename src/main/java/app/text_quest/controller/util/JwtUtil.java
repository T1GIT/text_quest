package app.text_quest.controller.util;

import app.text_quest.controller.util.enums.Period;
import app.text_quest.database.model.user.User;
import app.text_quest.security.util.secretFactory.types.JwtKeyFactory;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;


public abstract class JwtUtil {

    private final static JwtKeyFactory jwtKeyFactory = new JwtKeyFactory();

    private final static int PERIOD = Period.HOUR.getSec();
    private final static Key KEY = Keys.hmacShaKeyFor(jwtKeyFactory.create());

    public static int getPeriod() {
        return PERIOD;
    }

    public static String parseJwt(User user) {
        HashMap<String, String> userMap = new HashMap<>();
        userMap.put("id", String.valueOf(user.getId()));
        userMap.put("name", user.getName());
        return Jwts.builder()
                .setClaims(userMap)
                .setExpiration(new Date(System.currentTimeMillis() + PERIOD * 1000L))
                .signWith(KEY)
                .compact();
    }

    public static User parseUser(String jwt) throws SignatureException, MalformedJwtException {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(KEY)
                .build().parseClaimsJws(jwt).getBody();
        return new User(
                Long.parseLong((String) claims.get("id")),
                (String) claims.get("name")
        );
    }

}
