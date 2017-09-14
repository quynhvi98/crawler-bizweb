package com.higgsup.bizwebcrawler.controller.authentication;

/**
 * Created by viquy 2:41 PM 9/13/2017
 */
public class AuthCookie {
    private String cookie;

    public AuthCookie(String cookie) {
        this.cookie = cookie;
    }

    public AuthCookie() {
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }
}
