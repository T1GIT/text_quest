package app.controller.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Provides static methods for working with cookies.
 */
public abstract class CookieUtil {

    /**
     * Gets a cookie from the array of cookies by its name.
     *
     * @param cookies cookies array
     * @param name    cookie name
     * @return cookie object
     */
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

    /**
     * Gets a cookie from the httpServletRequest by its name.
     *
     * @param request for searching cookie
     * @param name    cookie name
     * @return cookie object
     */
    public static Cookie find(HttpServletRequest request, String name) {
        return find(request.getCookies(), name);
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
        Cookie cookie = new Cookie(name, String.valueOf(value));
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
