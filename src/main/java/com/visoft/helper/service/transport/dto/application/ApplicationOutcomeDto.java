package com.visoft.helper.service.transport.dto.application;

import com.visoft.helper.service.transport.dto.IdDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class ApplicationOutcomeDto extends IdDto {

    private String name;
    private Set<Long> folderIds;
}
