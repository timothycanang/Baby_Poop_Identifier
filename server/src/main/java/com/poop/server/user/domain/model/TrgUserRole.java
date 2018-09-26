package com.poop.server.user.domain.model;

import com.poop.server.core.domain.model.DateAudit;
import com.poop.server.user.domain.TrgEnumRole;

public interface TrgUserRole extends DateAudit {

    Long getId();

    void setId(Long id);

    TrgUser getUserId();

    void setUserId(TrgUser userId);


    TrgEnumRole getRole();

    void setRole(TrgEnumRole role);
}
