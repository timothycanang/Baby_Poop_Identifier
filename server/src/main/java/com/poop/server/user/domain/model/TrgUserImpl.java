package com.poop.server.user.domain.model;


import com.poop.server.core.domain.model.DateAuditImpl;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "EmgUser")
@Table(name = "dt_user")
public class TrgUserImpl extends DateAuditImpl implements TrgUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dt_user_id")
    private long userId;

    @Column(name = "dt_user_name", unique = true)
    private String userName;
    @Column(name = "dt_user_contact")
    private String userContact;
    @Column(name = "dt_user_email")
    private String userEmail;
    //  @Column(name= "dt_user_access_level_id")
//  private long dtUserAccessLevel;
    @Column(name = "dt_user_password")
    private String userPassword;
    @Column(name = "dt_user_active_status")
    private boolean userActiveStatus;

    @Column(name="dt_user_enabled")
    private boolean userEnabled;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "dt_user_role", joinColumns = @JoinColumn(name = "dt_user_id"), inverseJoinColumns = @JoinColumn(name = "dt_role_id"))
    private Set<TrgRoleImpl> roles;

    public TrgUserImpl() {
    }

    TrgUserImpl(TrgUserImpl user) {
        user.userActiveStatus = user.getUserActiveStatus();
        user.userContact = user.getUserContact();
        user.userEmail = user.getUserEmail();
        user.userId = user.getUserId();
        user.userName = user.getUsername();
        user.userPassword = user.getUserPassword();
        user.roles = user.getRoles();
        user.userEnabled = user.getUserEnabled();
    }


    public TrgUserImpl(String userName, String dtUserContact, String userEmail, String userPassword, boolean userActiveStatus, Set<TrgRoleImpl> roles) {
        this.userName = userName;
        this.userContact = dtUserContact;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userActiveStatus = userActiveStatus;
        this.roles = roles;
    }


    public long getUserId() {
        return userId;
    }

    public void setUserId(long dtUserId) {
        this.userId = dtUserId;
    }

    public String getUsername() {
        return userName;
    }

    public void setUsername(String dtUserName) {

        this.userName = dtUserName;
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

    public Set<TrgRoleImpl> getRoles() {
        return roles;
    }

    public void setRoles(Set<TrgRoleImpl> roles) {
        this.roles = roles;
    }

}
