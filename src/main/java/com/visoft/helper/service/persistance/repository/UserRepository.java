package com.visoft.helper.service.persistance.repository;

import com.visoft.helper.service.persistance.entity.user.User;

import java.util.Optional;

public interface UserRepository extends GeneralRepository<User> {

    Optional<User> findByLogin(String login);
}
