package com.htam25.minishop.security.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CookieUtil {

    public static void addRefreshToken(HttpServletResponse response, String token) {
        Cookie cookie = new Cookie("refreshToken", token);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(7 * 24 * 60 * 60);

        response.addCookie(cookie);
    }

    public static String getRefreshToken(HttpServletRequest request) {
        if(request.getCookies() == null) return null;

        for(Cookie c : request.getCookies()) {
            if("refreshToken".equals(c.getName())) {
                return c.getValue();
            }
        }
        return null;
    }

    public static void clear(HttpServletResponse response) {
        Cookie cookie = new Cookie("refreshToken", null);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(0);

        response.addCookie(cookie);
    }
}
