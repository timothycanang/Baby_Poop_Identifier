package com.poop.server.user.domain.dao;

import com.poop.server.user.domain.model.TrgUser;
//import com.poop.server.user.domain.model.EmgUserRole;
//import com.poop.server.user.domain.model.EmgUserRoleImpl;
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
    public List<TrgUser> findAll() throws Exception {

        Query q = em.createQuery("select e from EmgUser e ");
        return q.getResultList();

    }

    @Override
    public TrgUser findByUserName(String userName) throws Exception {

        Query q = em.createQuery("select e from EmgUser e where e.userName IN:userName")
                .setParameter("userName", userName.toLowerCase());
        return (TrgUser) q.getResultList().get(0);

    }

    @Override
    public TrgUser findByUserId(Long userId) throws Exception {

        Query q = em.createQuery("select e from EmgUser e where e.userId IN:userID")
                .setParameter("userID", userId);
        return (TrgUser) q.getResultList().get(0);

    }


    @Override
    public TrgUser save(TrgUser user) throws Exception {

        user.setCreatedAt(Timestamp.from(Instant.now()));
        em.persist(user);
        return findByUserName(user.getUsername());

    }

    @Override
    public void setUserRole(long uId, long roleId) throws Exception {
//        EmgUserRole role = new EmgUserRoleImpl();
//        role.setCreatedAt(Timestamp.from(Instant.now()));
//        role.setDt_role_id(roleId);
//        role.setUserId(uId);
//        em.persist(role);
    }
}
