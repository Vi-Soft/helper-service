package com.visoft.helper.service.transport.dto.tree;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TreeContentDto extends ApplicationTreeOutcomeDto {

    private String pathEn;
    private String pathRu;
    private String pathHe;

    @JsonIgnore
    private int orderNumber;
}
