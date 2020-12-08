package com.visoft.helper.service.transport.dto.authorization;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class LoginDto {

    @NotEmpty
    private String login;

    @NotEmpty
    private String password;
}
