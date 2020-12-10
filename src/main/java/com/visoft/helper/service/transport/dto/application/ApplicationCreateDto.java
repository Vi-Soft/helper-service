package com.visoft.helper.service.transport.dto.application;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class ApplicationCreateDto {

    @NotEmpty
    private String name;

}
