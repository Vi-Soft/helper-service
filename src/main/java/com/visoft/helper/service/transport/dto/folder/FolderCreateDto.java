package com.visoft.helper.service.transport.dto.folder;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class FolderCreateDto extends FolderUpdateDto {

    @NotNull
    private Long applicationId;

    private List<Long> additionApplicationIds;

    private Long parentId;
}
