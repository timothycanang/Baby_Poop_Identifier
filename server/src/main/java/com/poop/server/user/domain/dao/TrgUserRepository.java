package com.poop.server.user.domain.dao;

import com.poop.server.user.domain.model.TrgUserImpl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TrgUserRepository extends JpaRepository<TrgUserImpl,Integer> {
    Optional<TrgUserImpl> findByUsername(String username);
}
