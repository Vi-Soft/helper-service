package com.visoft.helper.service.transport.dto.tree;

import com.visoft.helper.service.transport.dto.multilanguage.MultiLanguageNameIdDto;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class ApplicationTreeOutcomeDto extends MultiLanguageNameIdDto {

    @NotNull
    private List<TreeContentDto> content;
}
