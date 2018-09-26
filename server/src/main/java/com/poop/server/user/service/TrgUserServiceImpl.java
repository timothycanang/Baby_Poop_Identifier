package com.poop.server.user.service;

import com.poop.server.user.controller.vo.RoleVo;
import com.poop.server.user.controller.vo.UserVo;
import com.poop.server.user.domain.TrgEnumRole;
import com.poop.server.user.domain.dao.TrgUserDao;
import com.poop.server.user.domain.model.TrgUser;
import com.poop.server.user.domain.model.TrgUserImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.poop.server.user.domain.TrgEnumRole.*;


@Service
public class TrgUserServiceImpl implements TrgUserService {
    private static final Logger LOG = LoggerFactory.getLogger(TrgUserServiceImpl.class);


    @Autowired
    private TrgUserDao emgUserDao;
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public TrgUserServiceImpl() {
    }


    @Override
    public TrgUser findUserByUsername(String username) {
        try {
            return emgUserDao.findByUserName(username);
        } catch (Exception e) {
            LOG.error("No user found " + e);
            return null;
        }

    }

    @Override
    public TrgUser saveUser(UserVo vo) throws Exception {

        if (findUserByUsername(vo.getUsername()) != null)
            throw new Exception("This username has been used!");

        try {
            TrgUser user = new TrgUserImpl();
            user.setUserPassword(passwordEncoder.encode(vo.getPassword()));
            user.setUserActiveStatus(true);
            user.setUserEnabled(true);
            user.setUserContact(vo.getContact());
            user.setUserEmail(vo.getEmail());
            user.setUsername(vo.getUsername());
            emgUserDao.save(user);
            return user;
        } catch (Exception e) {
            LOG.error("Error Registering User " + e);
            throw new Exception("Error While Registering User");
        }
    }


    @Override
    public void setUserRole(String username, TrgEnumRole role) throws Exception {
        TrgUser user = findUserByUsername(username);
            emgUserDao.setUserRole(user, role);
    }


    @Override
    public List<TrgUser> findAllUser() throws NullPointerException {
        try {
            return emgUserDao.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            LOG.debug("User not found"+e);
            return null;
        }
    }


    @Override
    public TrgUser getSignedInUser() {
        return null;
    }


}
