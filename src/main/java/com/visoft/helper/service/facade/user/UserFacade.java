package com.visoft.helper.service.facade.user;

import com.visoft.helper.service.persistance.entity.user.User;

import java.util.Optional;

public interface UserFacade {

    Optional<User> findByLogin(String login);

    Optional<User> findById(Long id);
}
