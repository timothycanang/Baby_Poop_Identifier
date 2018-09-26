package com.poop.server.user.domain.dao;

import com.poop.server.user.domain.TrgEnumRole;
import com.poop.server.user.domain.model.TrgUser;
//import com.poop.server.user.domain.model.EmgUserRole;
//import com.poop.server.user.domain.model.EmgUserRoleImpl;
import com.poop.server.user.domain.model.TrgUserRole;
import com.poop.server.user.domain.model.TrgUserRoleImpl;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;


@Transactional
@Repository
public class TrgUserDaoImpl implements TrgUserDao {


    @PersistenceContext
    EntityManager em;

    @Override
    public List<TrgUser> findAll() {
        Query q = em.createQuery("select e from User e ");
        return q.getResultList();
    }

    @Override
    public TrgUser findByUserName(String username) {
        Query q = em.createQuery("select e from User e where e.username IN:username")
                .setParameter("username", username.toLowerCase());
        return (TrgUser) q.getSingleResult();
    }

    @Override
    public TrgUser findByUserId(Long userId) {
        Query q = em.createQuery("select e from User e where e.userId IN:userID")
                .setParameter("userID", userId);
        return (TrgUser) q.getResultList().get(0);
    }


    @Override
    public TrgUser save(TrgUser user) {
        user.setCreatedAt(Timestamp.from(Instant.now()));
        em.persist(user);
        return findByUserName(user.getUsername());
    }

    @Override
    public void setUserRole(TrgUser user, TrgEnumRole role) {
        TrgUserRole userRole = new TrgUserRoleImpl();
        userRole.setCreatedAt(Timestamp.from(Instant.now()));
        userRole.setRole(role);
        userRole.setUserId(user);
        em.persist(userRole);
    }
}
