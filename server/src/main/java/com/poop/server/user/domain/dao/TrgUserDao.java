package com.poop.server.user.domain.dao;

import com.poop.server.user.domain.TrgEnumRole;
import com.poop.server.user.domain.model.TrgUser;

import java.util.List;

public interface TrgUserDao {


    List<TrgUser> findAll() ;

    TrgUser findByUserName(String userName) ;

    TrgUser findByUserId(Long userId) ;

    TrgUser save(TrgUser user) ;

    void setUserRole (TrgUser user, TrgEnumRole role) ;

}
