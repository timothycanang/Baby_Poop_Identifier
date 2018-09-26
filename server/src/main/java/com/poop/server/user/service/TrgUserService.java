package com.poop.server.user.service;

import com.poop.server.user.controller.vo.RoleVo;
import com.poop.server.user.controller.vo.UserVo;
import com.poop.server.user.domain.TrgEnumRole;
import com.poop.server.user.domain.model.TrgUser;

import java.util.List;
import java.util.Set;

public interface TrgUserService {

    TrgUser findUserByUsername(String username) throws Exception;

    TrgUser saveUser(UserVo user)throws Exception ;

    List<TrgUser> findAllUser()throws Exception;

    void setUserRole(String username, TrgEnumRole role)throws Exception ;

    TrgUser getSignedInUser ()throws Exception;

}
