package com.visoft.helper.service.transport.dto.multilanguage;

import com.visoft.helper.service.transport.dto.IdDto;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class MultiLanguageNameIdDto extends IdDto {

    @NotBlank
    @NotNull
    private String nameEn;

    @NotBlank
    @NotNull
    private String nameHe;

    @NotBlank
    @NotNull
    private String nameRu;
}
