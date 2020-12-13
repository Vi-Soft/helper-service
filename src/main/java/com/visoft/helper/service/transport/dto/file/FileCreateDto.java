package com.visoft.helper.service.transport.dto.file;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class FileCreateDto {

    @NotEmpty
    private String name;

    @NotEmpty
    private String path;

    @NotNull
    private Long folderId;
}
