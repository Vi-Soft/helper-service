package com.visoft.helper.service.transport.dto.tree;

import com.visoft.helper.service.transport.dto.MultiLanguageNameIdDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ApplicationTreeOutcomeDto extends MultiLanguageNameIdDto {

    private List<TreeContentDto> content;
}
