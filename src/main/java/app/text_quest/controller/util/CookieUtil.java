package app.text_quest.controller.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public abstract class CookieUtil {

    public static Cookie find(Cookie[] cookies, String name) {
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    return cookie;
                }
            }
        }
        return null;
    }

    public static Cookie find(HttpServletRequest request, String name) {
        return find(request.getCookies(), name);
    }

    public static void add(HttpServletResponse response, String name, Object value, int expiresIn) {
        Cookie cookie = new Cookie(name, String.valueOf(value));
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setMaxAge(expiresIn);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public static void remove(HttpServletResponse response, String name) {
        add(response, name, null, 0);
    }
}
