package com.opendmp.util;

/**
 * Created by pramod.sharma on 06/11/15.
 */

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class CookieUtil {

    private final static String COOKIE_NAME = "_od.uid";
    private final static int COOKIE_MAX_AGE_SECONDS = 31536000;
    private final static String COOKIE_PATH = "/";
    private final static String COOKIE_DOMAIN = ".opendmp.in";

    public static void setCookie(HttpServletRequest req, HttpServletResponse res)
    {

        Cookie existCookie = null;
        String uid = req.getParameter("uid");
        Cookie[]  cookie = req.getCookies();

        if (StringUtils.isEmpty(uid)) {
            uid = "s:" + Math.round(Math.random() * 1000000);
        }

        if(cookie != null) {
            for (int i = 0; i <= cookie.length; i++)
            {
                if (cookie[i].equals(COOKIE_NAME)) {
                    existCookie = cookie[i];
                    break;
                }
            }
        }

        if(existCookie == null) {
            createCookie(COOKIE_NAME, uid);
        }
        else {
            updateCookie(existCookie);
        }


        res.addCookie(existCookie);
    }


    private static  Cookie createCookie(String cookieName, String cookieValue) {
        Cookie cookie = new Cookie(cookieName, cookieValue);
        cookie.setPath(COOKIE_PATH);
        cookie.setDomain(COOKIE_DOMAIN);
        cookie.setMaxAge(COOKIE_MAX_AGE_SECONDS);
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        return cookie;
    }

    private static  Cookie updateCookie (Cookie existCookie) {
        Cookie cookie = new Cookie(existCookie.getName(), existCookie.getValue());
        cookie.setPath(existCookie.getPath());
        cookie.setDomain(existCookie.getDomain());
        cookie.setMaxAge(COOKIE_MAX_AGE_SECONDS);
        cookie.setHttpOnly(true);
        cookie.setSecure(false);

        return cookie;
    }
}