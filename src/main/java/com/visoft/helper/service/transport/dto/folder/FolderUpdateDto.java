package com.visoft.helper.service.transport.dto.folder;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class FolderUpdateDto {

    @NotEmpty
    private String name;

    @Min(0)
    private long folderOrder;

    private Long parentId;
}
