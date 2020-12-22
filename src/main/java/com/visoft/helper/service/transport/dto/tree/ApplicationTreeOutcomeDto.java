package com.visoft.helper.service.transport.dto.tree;

import com.visoft.helper.service.transport.dto.MultiLanguageNameDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ApplicationTreeOutcomeDto extends MultiLanguageNameDto {

    private List<TreeContentDto> content;
}
