package com.poop.server.security.controller;

public class TokenVo {

    private String token;


    public TokenVo(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
