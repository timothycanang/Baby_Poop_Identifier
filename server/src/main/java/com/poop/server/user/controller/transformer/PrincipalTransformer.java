package com.poop.server.user.controller.transformer;

import com.poop.server.user.domain.model.TrgUser;
import com.poop.server.user.controller.vo.RoleVo;
import com.poop.server.user.controller.vo.UserVo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PrincipalTransformer {


    public PrincipalTransformer() {
    }

     public UserVo toVo(TrgUser user) {
         UserVo vo = new UserVo();
         Set<RoleVo> authVos= new HashSet<RoleVo>();

         vo.setUsername(user.getUsername());
         vo.setContact(user.getUserContact());
         vo.setEmail(user.getUserEmail());

         if (!user.getRoles().isEmpty()) {
             authVos = user.getRoles()
                     .stream()
                     .map(role -> new RoleVo(role.getRole())).collect(Collectors.toSet());
         }

         vo.setRoles(authVos);

         return vo;
     }



     public List<UserVo> toVos (List<TrgUser> users){
        List<UserVo> vos = users.stream()
                .map(this::toVo)
                .collect(Collectors.toList());
         return vos;
     }


}
