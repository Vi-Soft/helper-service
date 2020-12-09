package com.visoft.helper.service.transport.dto.authorization;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class LoginDto {

    @NotEmpty
    @ApiModelProperty(example = "adminLog", required = true)
    private String login;

    @NotEmpty
    @ApiModelProperty(example = "adminPas", required = true)
    private String password;
}
