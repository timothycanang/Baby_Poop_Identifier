package com.poop.server.user.controller.api;


import com.poop.server.user.controller.transformer.PrincipalTransformer;
import com.poop.server.user.controller.vo.UserVo;
import com.poop.server.user.domain.model.TrgUser;
import com.poop.server.user.service.TrgUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    TrgUserService userService;
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    /**
     * @return TrgUser
     * @throws Exception
     */
    @GetMapping("/all")
    public ResponseEntity<List<UserVo>> findAllUsers() throws Exception {
        List<TrgUser> users = userService.findAllUser();
        if (users == null) throw new Exception("No User found");

        return new ResponseEntity<List<UserVo>>(new PrincipalTransformer().toVos(users), HttpStatus.OK);
    }


    /**
     * @param username
     * @return TrgUser
     * @throws Exception
     */
    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public ResponseEntity<UserVo> findUserByUsername(@PathVariable String username) throws Exception {
        TrgUser user = userService.findUserByUsername(username);
        if (user == null) throw new Exception("User not found");
        return new ResponseEntity<UserVo>(new PrincipalTransformer().toVo(user), HttpStatus.OK);

    }

    /**
     * @param user
     * @return TrgUser
     * @throws Exception
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<UserVo> updateUser(@RequestBody UserVo user) throws Exception {
        TrgUser saved = userService.saveUser(user);
        if (saved == null) throw new Exception("Error While Updating User");
        return new ResponseEntity<UserVo>(new PrincipalTransformer().toVo(saved), HttpStatus.OK);
    }


}
