package com.poop.server.user.controller.vo;

public class RoleVo {
    public RoleVo() {
    }

    private long authId;
private String authName;

    public RoleVo(long authId, String authName) {
        this.authId = authId;
        this.authName = authName;
    }

    public String getAuthName() {
        return authName;
    }

    public void setAuthName(String authName) {
        this.authName = authName;
    }

    public long getAuthId() {
        return authId;
    }

    public void setAuthId(long authId) {
        this.authId = authId;
    }
}
