package com.visoft.helper.service.persistance.repository;

import com.visoft.helper.service.persistance.entity.Token;
import com.visoft.helper.service.persistance.entity.user.User;

import java.util.Optional;

public interface TokenRepository extends GeneralRepository<Token> {

    Optional<Token> findByToken(String token);

    Optional<Token> findByUserId(Long id);

    void deleteByUser(User user);

    boolean existsByToken(String token);

    boolean existsByUser(User user);
}
