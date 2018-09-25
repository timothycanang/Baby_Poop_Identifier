package com.poop.server.core.domain.model;

public interface UserDateAudit {

     Long getCreatedBy();

     void setCreatedBy(Long createdBy);

     Long getUpdatedBy();

     void setUpdatedBy(Long updatedBy);
}
