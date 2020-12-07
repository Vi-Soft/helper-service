package com.visoft.helper.service.configuration.security;

import com.google.common.io.BaseEncoding;
import com.visoft.helper.service.persistance.entity.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Configuration
public class TokenHandler {

    private final Long tokenExpirationTime;
    private final SecretKey secretKey;

    public TokenHandler(
            @Value("${jwt.secret-key}") String jwtKey,
            @Value("${jwt.exp.hours}") Long tokenExpirationTime) {
        this.tokenExpirationTime = tokenExpirationTime;
        byte[] decodedKey = BaseEncoding.base64().decode(jwtKey);
        secretKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    public Optional<Long> extractUserId(@NonNull String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            Claims body = claimsJws.getBody();
            return Optional
                    .ofNullable(body.get("userId"))
                    .map(userId -> (Long) userId);

        } catch (RuntimeException e) {
            return Optional.empty();
        }
    }

    public String generateAccessToken(User user) {
        return Jwts.builder()
                .claim("role", user.getAuthorities())
                .claim("expiration",
                        Instant.now()
                                .plus(tokenExpirationTime, ChronoUnit.HOURS)
                                .toEpochMilli()
                ).claim("userId", user.getId())
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }
}
