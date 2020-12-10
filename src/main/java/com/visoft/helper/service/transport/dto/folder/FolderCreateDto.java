package com.visoft.helper.service.transport.dto.folder;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class FolderCreateDto {

    @NotEmpty
    private String name;

    @Min(0)
    private long folderOrder;

    @NotNull
    private Long applicationId;

    private Long parentId;
}
