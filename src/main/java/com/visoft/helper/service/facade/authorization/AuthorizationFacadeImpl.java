package com.visoft.helper.service.facade.authorization;

import com.visoft.helper.service.exception.BadCredentialException;
import com.visoft.helper.service.facade.token.TokenFacade;
import com.visoft.helper.service.facade.user.UserFacade;
import com.visoft.helper.service.persistance.entity.Token;
import com.visoft.helper.service.persistance.entity.user.User;
import com.visoft.helper.service.transport.dto.authorization.LoginDto;
import com.visoft.helper.service.transport.dto.authorization.LoginOutcomeDto;
import com.visoft.helper.service.transport.mapper.AuthorizationMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthorizationFacadeImpl implements AuthorizationFacade {

    private final UserFacade userFacade;
    private final PasswordEncoder passwordEncoder;
    private final TokenFacade tokenFacade;
    private final AuthorizationMapper authorizationMapper;

    @Override
    public LoginOutcomeDto login(LoginDto loginDto) {
        log.info("Start login {}", loginDto.getLogin());
        User actor = userFacade.findByLogin(loginDto.getLogin())
                .orElseThrow(BadCredentialException::new);

        validatePassword(actor, loginDto.getPassword());
        LoginOutcomeDto loginOutcomeDto = authorizationMapper.toDto(
                tokenFacade.save(manageToken(actor)).getToken());
        log.info("Login successful {}", actor.getLogin());
        return loginOutcomeDto;
    }

    @Override
    public void logout() {
        User actor = getActorFromContext();
        log.info("Start logout {}", actor.getLogin());
        tokenFacade.deleteByUser(actor);
        log.info("Logout successful {}", actor.getLogin());
    }

    private void validatePassword(User actor, String password) {
        if (!passwordEncoder.matches(password, actor.getPassword())) {
            log.info("Invalid credentials: {}", actor.getLogin());
            throw new BadCredentialException();
        }
    }

    private Token manageToken(User user) {
        return tokenFacade.findByUser(user)
                .map(token -> tokenFacade.updateExistedToken(token, user))
                .orElseGet(() -> tokenFacade.createNewToken(user));
    }

    private User getActorFromContext() {
        return Optional.of(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .map(authentication -> (User) authentication.getPrincipal())
                .orElseGet(User::new);
    }
}
