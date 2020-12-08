package com.visoft.helper.service.transport.mapper;

import com.visoft.helper.service.transport.dto.authorization.LoginOutcomeDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface AuthorizationMapper {

    @Mapping(target = "token", source = "token")
    LoginOutcomeDto toDto(String token);
}
