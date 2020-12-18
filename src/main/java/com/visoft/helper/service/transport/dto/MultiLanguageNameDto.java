package com.visoft.helper.service.transport.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class MultiLanguageNameDto {

    @NotEmpty
    private String nameEn;

    @NotEmpty
    private String nameHe;

    @NotEmpty
    private String nameRu;
}
