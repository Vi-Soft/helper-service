package com.visoft.helper.service.service.token;

import com.visoft.helper.service.persistance.entity.Token;
import com.visoft.helper.service.persistance.entity.user.User;
import com.visoft.helper.service.persistance.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final TokenRepository tokenRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<Token> findByToken(String token) {
        return tokenRepository.findByToken(token);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Token> findByUser(User user) {
        return tokenRepository.findByUserId(user.getId());
    }

    @Override
    public Token save(Token token) {
        return tokenRepository.save(token);
    }

    @Override
    public void deleteByUser(User user) {
        tokenRepository.deleteByUser(user);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByToken(String token) {
        return tokenRepository.existsByToken(token);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByUser(User user) {
        return tokenRepository.existsByUser(user);
    }
}
