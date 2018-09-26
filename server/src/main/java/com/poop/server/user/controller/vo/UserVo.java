package com.poop.server.user.controller.vo;

import java.util.Set;

public class UserVo {

    private String username;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    private Long userId;
    private String email;
    private String contact;
    private String password;
    private Set<RoleVo> roles;

    public UserVo() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Set<RoleVo> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleVo> roles) {
        this.roles = roles;
    }

    public UserVo(String username, String email, String contact, Set<RoleVo> roles) {
        this.username = username;
        this.email = email;
        this.contact = contact;
        this.roles = roles;
    }
}
