package com.poop.server.user.service;

import com.poop.server.user.domain.dao.TrgUserRepository;
import com.poop.server.user.domain.model.TrgCustomUserDetails;
import com.poop.server.user.domain.model.TrgUserImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TrgCustomUserDetailsService implements UserDetailsService {

    private final TrgUserRepository emgUserRepository;

    @Autowired
    public TrgCustomUserDetailsService(TrgUserRepository emgUserRepository) {
        this.emgUserRepository = emgUserRepository;
    }

            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<TrgUserImpl> optionalUser = emgUserRepository.findByUsername(username);

        optionalUser.orElseThrow(()-> new UsernameNotFoundException("Username Not Found"));

        return optionalUser.map(
                TrgCustomUserDetails::new
        ).get();

    }
}
