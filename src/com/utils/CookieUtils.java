package com.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieUtils {
    public static String getUserName(Cookie[] cookies){
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals("username")){
                return cookie.getValue();
            }
        }
        return null;
    }
}
