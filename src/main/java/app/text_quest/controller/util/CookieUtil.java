package app.text_quest.controller.util;

import app.text_quest.controller.util.enums.Period;

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

    public static Cookie create(String name, Object value, int expiresIn) {
        Cookie cookie = new Cookie(name, String.valueOf(value));
        cookie.setHttpOnly(true);
        cookie.setMaxAge(expiresIn);
        cookie.setPath("/");
        return cookie;
    }

    public static Cookie create(String name, Object value, Period period) {
        return create(name, value, period.getSec());
    }

    public static void create(HttpServletResponse response, String name, Object value, Period period) {
        response.addCookie(create(name, value, period));
    }

    public static void create(HttpServletResponse response, String name, Object value, int expiresIn) {
        response.addCookie(create(name, value, expiresIn));
    }

    public static Cookie remove(String name) {
        return create(name, "", 0);
    }

    public static void remove(HttpServletResponse response, String name) {
        response.addCookie(remove(name));
    }
}
