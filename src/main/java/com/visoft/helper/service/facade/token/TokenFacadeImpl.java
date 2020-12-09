package com.visoft.helper.service.facade.token;

import com.visoft.helper.service.configuration.security.TokenHandler;
import com.visoft.helper.service.exception.TokenAlreadyExistsException;
import com.visoft.helper.service.persistance.entity.Token;
import com.visoft.helper.service.persistance.entity.user.User;
import com.visoft.helper.service.service.token.TokenService;
import com.visoft.helper.service.transport.mapper.TokenMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TokenFacadeImpl implements TokenFacade {

    private final TokenService tokenService;
    private final TokenHandler tokenHandler;
    private final TokenMapper tokenMapper;

    @Value("${jwt.exp.hours}")
    private Long tokenExpirationTime;

    @Override
    public Optional<Token> findByUser(User user) {
        return tokenService.findByUser(user);
    }

    @Override
    public Token updateExistedToken(Token token, User user) {
        token.setToken(tokenHandler.generateAccessToken(user));
        token.setExpiration(makeExpirationPoint());
        return token;
    }

    @Override
    public Token createNewToken(User user) {
        Token token = tokenMapper.toEntity(
                makeExpirationPoint(),
                tokenHandler.generateAccessToken(user),
                user
        );

        validateCreateNewToken(token);
        return token;
    }

    @Override
    public Token save(Token token) {
        return tokenService.save(token);
    }

    @Override
    public void deleteByUser(User user) {
        tokenService.deleteByUser(user);
    }

    @Override
    public Optional<Token> findByToken(String token) {
        return tokenService.findByToken(token);
    }

    private Instant makeExpirationPoint() {
        return Instant.now()
                .plus(tokenExpirationTime, ChronoUnit.HOURS);
    }

    private void validateCreateNewToken(Token token) {
        existsByTokenUnsafe(token.getToken());
        existsByUserUnsafe(token.getUser());
    }

    private void existsByTokenUnsafe(String token) {
        if (!tokenService.existsByToken(token)) {
            throw new TokenAlreadyExistsException();
        }
    }

    private void existsByUserUnsafe(User user) {
        if (!tokenService.existsByUser(user)) {
            throw new TokenAlreadyExistsException();
        }
    }
}
