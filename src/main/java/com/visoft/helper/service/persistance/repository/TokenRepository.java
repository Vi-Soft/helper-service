package com.visoft.helper.service.persistance.repository;

import com.visoft.helper.service.persistance.entity.Token;

import java.util.Optional;

public interface TokenRepository extends GeneralRepository<Token> {

    Optional<Token> findByToken(String token);
}
