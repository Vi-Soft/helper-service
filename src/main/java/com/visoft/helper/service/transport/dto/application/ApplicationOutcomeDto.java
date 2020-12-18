package com.visoft.helper.service.transport.dto.application;

import com.visoft.helper.service.transport.dto.MultiLanguageNameIdDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ApplicationOutcomeDto extends MultiLanguageNameIdDto {

    private List<Long> folderIds;
}
