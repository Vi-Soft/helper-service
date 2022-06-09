package com.visoft.helper.service.transport.dto.file;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileCreateDto extends FileUpdateDto {

    private Long folderId;
    @NotNull
    private Long applicationId;
}
