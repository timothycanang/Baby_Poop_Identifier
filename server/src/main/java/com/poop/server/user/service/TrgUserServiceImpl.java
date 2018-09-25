package com.poop.server.user.service;

import com.poop.server.user.controller.vo.RoleVo;
import com.poop.server.user.controller.vo.UserVo;
import com.poop.server.user.domain.dao.TrgUserDao;
import com.poop.server.user.domain.model.TrgRole;
import com.poop.server.user.domain.model.TrgRoleImpl;
import com.poop.server.user.domain.model.TrgUser;
import com.poop.server.user.domain.model.TrgUserImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class TrgUserServiceImpl implements TrgUserService {


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
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public TrgUser saveUser(UserVo vo) {

        try {

            Set<TrgRoleImpl> roles = new HashSet<>();

            TrgUser user = new TrgUserImpl();
            user.setUserPassword( passwordEncoder.encode(vo.getPassword()) );
            user.setUserActiveStatus(true);
            user.setUserEnabled(true);
            user.setUserContact(vo.getContact());
            user.setUserEmail(vo.getEmail());
            user.setUsername(vo.getUsername());
            user.setRoles(roles);

            TrgUser res = emgUserDao.save(user);
            TrgUser u=emgUserDao.findByUserName(res.getUsername());

            return u;


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<TrgUser> findAllUser() {
        try {
            return emgUserDao.findAll() ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public TrgUser getSignedInUser() {
        return null;
    }


}
