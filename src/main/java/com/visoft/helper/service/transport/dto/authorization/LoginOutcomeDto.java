package com.visoft.helper.service.transport.dto.authorization;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class LoginOutcomeDto {

    @NotNull
    private String token;
}
