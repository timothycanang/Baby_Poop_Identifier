package com.poop.server.user.controller.transformer;

import com.poop.server.user.domain.model.TrgRole;
import com.poop.server.user.domain.model.TrgUser;
import com.poop.server.user.controller.vo.RoleVo;
import com.poop.server.user.controller.vo.UserVo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
             for (TrgRole role :user.getRoles()){
                 RoleVo roleVo = new RoleVo(role.getAccessLevelId(),role.getAccessLevelRole());
                 authVos.add(roleVo);
             }
         }

         vo.setAuthorities(authVos);

         return vo;
     }



     public List<UserVo> toVos (List<TrgUser> users){
        List<UserVo> vos = new ArrayList<>();
        for(TrgUser user :users){
            UserVo vo = new UserVo();
            Set<RoleVo> authVos= new HashSet<RoleVo>();

            vo.setUsername(user.getUsername());
            vo.setContact(user.getUserContact());
            vo.setEmail(user.getUserEmail());

            for (TrgRole role :user.getRoles()){
                RoleVo roleVo = new RoleVo(role.getAccessLevelId(),role.getAccessLevelRole());
                authVos.add(roleVo);
            }

            vo.setAuthorities(authVos);




        }
      return vos;
     }


}
