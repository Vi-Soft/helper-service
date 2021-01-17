package com.visoft.helper.service.transport.dto.authorization;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class LoginDto {

    @NotBlank
    @NotNull
    @ApiModelProperty(example = "adminLog", required = true)
    private String login;

    @NotBlank
    @NotNull
    @ApiModelProperty(example = "adminPas", required = true)
    private String password;
}
