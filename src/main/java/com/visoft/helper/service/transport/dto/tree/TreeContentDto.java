package com.visoft.helper.service.transport.dto.tree;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class TreeContentDto extends ApplicationTreeOutcomeDto {

    @NotNull
    private String pathEn;

    @NotNull
    private String pathRu;

    @NotNull
    private String pathHe;

    @JsonIgnore
    private int orderNumber;
}
