package com.poop.server.user.service;

import com.poop.server.user.controller.vo.UserVo;
import com.poop.server.user.domain.model.TrgUser;

import java.util.List;

public interface TrgUserService {

    TrgUser findUserByUsername(String username);

    TrgUser saveUser(UserVo user);

    List<TrgUser> findAllUser();

    TrgUser getSignedInUser ();

}
