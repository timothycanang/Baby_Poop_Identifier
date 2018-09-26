package com.poop.server.user.domain.model;


import com.poop.server.core.domain.model.DateAuditImpl;
import com.poop.server.user.domain.TrgEnumRole;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name="UserRole")
@Table(name ="trg_user_role")
public class TrgUserRoleImpl extends DateAuditImpl implements TrgUserRole, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private Long id;

    @Column(name = "role")
    private TrgEnumRole role;

    @JoinColumn(name = "user_id")
    @ManyToOne(targetEntity = TrgUserImpl.class)
    private TrgUser userId;

    public TrgUserRoleImpl() {
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public TrgUser getUserId() {
        return userId;
    }

    @Override
    public void setUserId(TrgUser userId) {
        this.userId = userId;
    }

    @Override
    public TrgEnumRole getRole() {
        return role;
    }

    @Override
    public void setRole(TrgEnumRole role) {
        this.role = role;
    }
}
