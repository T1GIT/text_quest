package app.controller.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * Provides static methods for working with cookies.
 */
public abstract class CookieUtil {

    /**
     * Gets a cookie from the array of cookies by its name.
     *
     * @param cookies cookies array
     * @param name    cookie name
     * @return cookie object or null if cookie was not found
     */
    public static Cookie getCookie(Cookie[] cookies, String name) {
        if (cookies == null) {
            return null;
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(name)) {
                return cookie;
            }
        }
        return null;
    }

    /**
     * Gets a cookie from the array of cookies by its name.
     *
     * @param cookies cookies array
     * @param name    cookie name
     * @return value of the cookie or null if cookie was not found
     */
    public static String get(Cookie[] cookies, String name) {
        Cookie cookie = getCookie(cookies, name);
        if (cookie != null) {
            return URLDecoder.decode(cookie.getValue(), StandardCharsets.UTF_8);
        }
        return null;
    }

    /**
     * Gets a cookie from the httpServletRequest by its name.
     *
     * @param request for searching cookie
     * @param name    cookie name
     * @return value of the cookie or null if cookie was not found
     */
    public static String get(HttpServletRequest request, String name) {
        return get(request.getCookies(), name);
    }

    /**
     * Creates, configures and adds cookie object to the response.
     *
     * @param response  target to add cookie
     * @param name      of the cookie
     * @param value     of the cookie
     * @param expiresIn life time of the cookie in seconds
     */
    public static void add(HttpServletResponse response, String name, Object value, int expiresIn) {
        Cookie cookie = new Cookie(name, URLEncoder.encode(String.valueOf(value), StandardCharsets.UTF_8));
        cookie.setHttpOnly(true);
        cookie.setMaxAge(expiresIn);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    /**
     * Removes cookie in the response.
     *
     * @param response to remove
     * @param name     of the cookie to delete
     */
    public static void remove(HttpServletResponse response, String name) {
        add(response, name, null, 0);
    }
}
