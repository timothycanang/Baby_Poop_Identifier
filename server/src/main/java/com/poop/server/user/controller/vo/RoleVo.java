package com.poop.server.user.controller.vo;


import com.poop.server.user.domain.TrgEnumRole;

public class RoleVo {

    private TrgEnumRole role;

    public RoleVo() {
    }

    public RoleVo(TrgEnumRole role) {
        this.role = role;
    }

    public TrgEnumRole getRole() {
        return role;
    }

    public void setRole(TrgEnumRole role) {
        this.role = role;
    }
}
