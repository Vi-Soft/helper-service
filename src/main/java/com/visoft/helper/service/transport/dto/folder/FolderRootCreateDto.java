package com.visoft.helper.service.transport.dto.folder;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class FolderRootCreateDto {

    @NotEmpty
    private String name;

    @NotNull
    private Long applicationId;
}
