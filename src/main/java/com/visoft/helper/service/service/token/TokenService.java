package com.visoft.helper.service.service.token;

import com.visoft.helper.service.persistance.entity.Token;
import com.visoft.helper.service.persistance.entity.user.User;

import java.util.Optional;

public interface TokenService {

    Optional<Token> findByToken(String token);

    Optional<Token> findByUser(User user);

    Token save(Token token);

    void deleteByUser(User user);

    boolean existsByToken(String token);

    boolean existsByUser(User user);
}
