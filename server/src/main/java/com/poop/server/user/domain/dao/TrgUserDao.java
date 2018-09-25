package com.poop.server.user.domain.dao;

import com.poop.server.user.domain.model.TrgUser;

import java.util.List;

public interface TrgUserDao {


    List<TrgUser> findAll() throws Exception;

    TrgUser findByUserName(String userName) throws Exception;

    TrgUser findByUserId(Long userId) throws Exception;

    TrgUser save(TrgUser user) throws Exception;

    void setUserRole (long uId, long roleId) throws Exception;

}
