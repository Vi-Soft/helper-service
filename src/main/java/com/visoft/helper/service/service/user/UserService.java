package com.visoft.helper.service.service.user;

import com.visoft.helper.service.persistance.entity.user.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {

    Optional<User> findById(Long id);

    Optional<User> findByLogin(String login);
}
