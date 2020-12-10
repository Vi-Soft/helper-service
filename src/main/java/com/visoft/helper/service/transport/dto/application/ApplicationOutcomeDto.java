package com.visoft.helper.service.transport.dto.application;

import com.visoft.helper.service.transport.dto.IdDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationOutcomeDto extends IdDto {

    private String name;
    private Long rootFolderId;
}
