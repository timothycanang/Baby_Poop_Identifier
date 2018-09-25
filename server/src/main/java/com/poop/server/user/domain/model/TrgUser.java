package com.poop.server.user.domain.model;

import com.poop.server.core.domain.model.DateAudit;

import java.util.Set;

public interface TrgUser extends DateAudit {

    long getUserId();

    void setUserId(long dtUserId);


    String getUsername();

    void setUsername(String dtUserName);


    String getUserContact();

    void setUserContact(String dtUserContact);


    String getUserEmail();

    void setUserEmail(String dtUserEmail);


    String getUserPassword();

    void setUserPassword(String dtUserPassword);


    boolean getUserActiveStatus();

    void setUserActiveStatus(boolean dtUserActiveStatus);


    boolean getUserEnabled();
    void setUserEnabled(boolean enabled);

     Set<TrgRoleImpl> getRoles();

     void setRoles(Set<TrgRoleImpl> roles);
}
