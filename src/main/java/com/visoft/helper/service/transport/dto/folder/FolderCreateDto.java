package com.visoft.helper.service.transport.dto.folder;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class FolderCreateDto extends FolderUpdateDto {

    @NotNull
    private Long applicationId;

    private Long parentId;
}
