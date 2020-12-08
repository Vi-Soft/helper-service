package com.visoft.helper.service.configuration.security;

import com.visoft.helper.service.facade.token.TokenFacade;
import com.visoft.helper.service.facade.user.UserFacade;
import com.visoft.helper.service.persistance.entity.Token;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class TokenAuthService {

    private static final String AUTHENTICATION_HEADER_NAME = "Authorization";
    private static final String BEARER_TOKEN_PREFIX = "Bearer";
    private final TokenFacade tokenFacade;
    private final UserFacade userFacade;
    private final TokenHandler tokenHandler;

    private static String extractBearerTokenValue(String string) {
        return string != null ?
                string.startsWith(BEARER_TOKEN_PREFIX) ?
                        string.substring(BEARER_TOKEN_PREFIX.length()).trim() :
                        string :
                "";
    }

    private static String pullToken(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader(TokenAuthService.AUTHENTICATION_HEADER_NAME))
                .orElseGet(() -> TokenAuthService.BEARER_TOKEN_PREFIX + " " + request.getParameter("token"));
    }

    public Optional<Authentication> getAuthentication(@NonNull HttpServletRequest request) {
        return getTokenFromRequest(request)
                .map(this::authenticationByTokenValue)
                .orElseGet(this::nullAuthentication);
    }

    private Optional<Authentication> authenticationByTokenValue(String tokenValue) {
        return Optional.of(tokenValue)
                .flatMap(tokenHandler::extractUserId)
                .flatMap(userFacade::findById)
                .map(UserAuthentication::new);
    }

    private Optional<Authentication> nullAuthentication() {
        return Optional.of(new UserAuthentication(null));
    }

    private Optional<String> getTokenFromRequest(@NonNull HttpServletRequest request) {
        final String authenticationHeader = TokenAuthService.pullToken(request);

        return tokenFacade.findByToken(extractBearerTokenValue(authenticationHeader))
                .filter(this::isNotExpired)
                .map(Token::getToken);
    }

    private boolean isNotExpired(Token token) {
        return Instant.now().isBefore(token.getExpiration());
    }
}
