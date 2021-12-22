package org.campus.webshop.web.util;

import javax.servlet.http.Cookie;

public class WebUtils {

    public static String getUserToken(Cookie[] cookies) {
        if (cookies != null) {
            for (javax.servlet.http.Cookie cookie : cookies) {
                if (cookie.getName().equals("user-token")) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
