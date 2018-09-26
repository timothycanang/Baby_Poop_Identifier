package com.poop.server.user.domain.model;


import com.poop.server.core.domain.model.DateAuditImpl;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "User")
@Table(name = "trg_user")
public class TrgUserImpl extends DateAuditImpl implements TrgUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long userId;

    @Column(name = "username", unique = true)
    private String username;
    @Column(name = "contact")
    private String userContact;
    @Column(name = "email")
    private String userEmail;
    //  @Column(name= "dt_user_access_level_id")
//  private Long dtUserAccessLevel;
    @Column(name = "password")
    private String userPassword;
    @Column(name = "active_status")
    private boolean userActiveStatus;
    @Column(name="user_enabled")
    private boolean userEnabled;

    @OneToMany(mappedBy="userId")
    private Set<TrgUserRoleImpl> roles;

    public TrgUserImpl() {
    }

    TrgUserImpl(TrgUserImpl user) {
        user.userActiveStatus = user.getUserActiveStatus();
        user.userContact = user.getUserContact();
        user.userEmail = user.getUserEmail();
        user.userId = user.getUserId();
        user.username = user.getUsername();
        user.userPassword = user.getUserPassword();
        user.roles = user.getRoles();
        user.userEnabled = user.getUserEnabled();
    }


    public TrgUserImpl(String username, String dtUserContact, String userEmail, String userPassword, boolean userActiveStatus, Set<TrgUserRoleImpl> roles) {
        this.username = username;
        this.userContact = dtUserContact;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userActiveStatus = userActiveStatus;
        this.roles = roles;
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long dtUserId) {
        this.userId = dtUserId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String dtUserName) {

        this.username = dtUserName;
    }

    public String getUserContact() {
        return userContact;
    }

    public void setUserContact(String dtUserContact) {
        this.userContact = dtUserContact;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String dtUserEmail) {
        this.userEmail = dtUserEmail;
    }

    public String getUserPassword() {

        return this.userPassword;
    }

    public void setUserPassword(String dtUserPassword) {

        this.userPassword = dtUserPassword;
    }

    public boolean getUserActiveStatus() {
        return userActiveStatus;
    }

    public void setUserActiveStatus(boolean dtUserActiveStatus) {
        this.userActiveStatus = dtUserActiveStatus;
    }
    @Override
    public boolean getUserEnabled() {
        return this.userEnabled;
    }
    @Override
    public void setUserEnabled(boolean enabled) {
        this.userEnabled = enabled;
    }

    @Override
    public Set<TrgUserRoleImpl> getRoles() {
        return roles;
    }

    public void setRoles(Set<TrgUserRoleImpl> roles) {
        this.roles = roles;
    }
}
