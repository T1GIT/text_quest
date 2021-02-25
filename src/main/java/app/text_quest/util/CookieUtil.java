package app.text_quest.util;

import javax.servlet.http.Cookie;


@Deprecated
public class CookieUtil {

    public static Cookie getByName(Cookie[] cookies, String name) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(name)) return cookie;
        }
        return null;
    }
}
