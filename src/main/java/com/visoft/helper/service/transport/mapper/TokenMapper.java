package com.visoft.helper.service.transport.mapper;

import com.visoft.helper.service.persistance.entity.Token;
import com.visoft.helper.service.persistance.entity.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.Instant;

@Mapper
public interface TokenMapper {

    @Mapping(target = "token", source = "token")
    @Mapping(target = "expiration", source = "expiration")
    @Mapping(target = "user", source = "user")
    Token toEntity(
            Instant expiration,
            String token,
            User user
    );
}
