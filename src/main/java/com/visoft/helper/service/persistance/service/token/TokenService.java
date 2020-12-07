package com.visoft.helper.service.persistance.service.token;

import com.visoft.helper.service.persistance.entity.Token;

import java.util.Optional;

public interface TokenService {

    Optional<Token> findByToken(String token);
}
