package com.poop.server.user.controller.api;


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


    @GetMapping("/all")
    public ResponseEntity<List<TrgUser>> findAllUsers() {
        return new ResponseEntity<>(userService.findAllUser(),HttpStatus.OK) ;
    }


    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public ResponseEntity<TrgUser> findUserByUsername(@PathVariable String username) {
        TrgUser user = userService.findUserByUsername(username);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public TrgUser updateUser(@RequestBody UserVo user) {
        return userService.saveUser(user);
    }


}
