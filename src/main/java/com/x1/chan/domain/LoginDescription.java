package com.x1.chan.domain;

public enum LoginDescription {
    LOGIN("로그인"),
    LOGOUT("로그아웃"),
    NAVER_LOGIN("네이버 로그인");

    private final String loginDescription;

    LoginDescription(String loginDescription) {
        this.loginDescription = loginDescription;
    }

    public String getValue(){
        return loginDescription;
    }
}
