package com.poop.server.core.domain.model;

import java.sql.Timestamp;

public interface DateAudit {

    Timestamp getCreatedAt();

    void setCreatedAt(Timestamp createdAt);

    Timestamp getUpdatedAt();

    void setUpdatedAt(Timestamp updatedAt);


}
