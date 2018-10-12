package com.poop.server.security.controller;

import com.poop.server.user.controller.vo.UserVo;
import com.poop.server.user.domain.TrgEnumRole;
import com.poop.server.user.domain.model.TrgUser;
import com.poop.server.user.service.TrgUserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import java.util.Date;

@RestController
@RequestMapping("/res")
public class SecurityController {

    @Autowired
    TrgUserService userService;
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    /**
     * @param input
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<TokenVo> login(@RequestBody SecurityVo input) throws Exception {
        if (input.getUsername() == null || input.getPassword() == null) {
            throw new ServletException("Please fill in username and password");
        }

        TrgUser user = userService.findUserByUsername(input.getUsername());
        if(user == null) throw new Exception("User not found");
        String pwd = user.getUserPassword();

        if (!passwordEncoder.matches(input.getPassword(), pwd)) {
            throw new ServletException("Invalid login. Please check your username and password");
        }
        return new ResponseEntity<>(new TokenVo(Jwts.builder().setSubject(input.getUsername()).claim("roles", "user").setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "secretkey").compact()), HttpStatus.OK);
    }

    /**
     * @param user
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/register/admin", method = RequestMethod.POST)
    public ResponseEntity<String> registerUser(@RequestBody UserVo user) throws Exception {
        TrgUser newUser = userService.saveUser(user);
        userService.setUserRole(newUser.getUsername(), TrgEnumRole.ADMIN);
        return new ResponseEntity<String>(newUser.getUsername() + " Created", HttpStatus.OK);
    }


    /**
     * @param user
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/register/user", method = RequestMethod.POST)
    public ResponseEntity<String> registerAdmin(@RequestBody UserVo user) throws Exception {
        TrgUser newUser = userService.saveUser(user);
        userService.setUserRole(newUser.getUsername(), TrgEnumRole.USER);
        return new ResponseEntity<String>(newUser.getUsername() + " Created", HttpStatus.OK);
    }

}
