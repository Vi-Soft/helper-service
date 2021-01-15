package com.visoft.helper.service.transport.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class MultiLanguageNameDto {

    @NotEmpty
    @NotNull
    private String nameEn;

    @NotEmpty
    @NotNull
    private String nameHe;

    @NotEmpty
    @NotNull
    private String nameRu;
}
