package com.visoft.helper.service.facade.token;

import com.visoft.helper.service.persistance.entity.Token;
import com.visoft.helper.service.persistance.entity.user.User;

import java.util.Optional;

public interface TokenFacade {

    Optional<Token> findByUser(User user);

    Token updateExistedToken(Token token, User user);

    Token createNewToken(User user);

    Token save(Token token);

    void deleteByUser(User user);

    Optional<Token> findByToken(String token);
}
