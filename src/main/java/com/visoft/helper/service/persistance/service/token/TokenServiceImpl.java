package com.visoft.helper.service.persistance.service.token;

import com.visoft.helper.service.persistance.entity.Token;
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
}
