package com.x1.chan.domain;

public enum LoginDescription {
    LOGIN("로그인"),
    LOGOUT("로그아웃");

    private final String loginDescription;

    LoginDescription(String loginDescription) {
        this.loginDescription = loginDescription;
    }

    public String getValue(){
        return loginDescription;
    }
}
