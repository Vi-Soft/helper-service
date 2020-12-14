package com.visoft.helper.service.transport.dto.folder;

import com.visoft.helper.service.transport.dto.OrderNumberDto;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class FolderUpdateDto extends OrderNumberDto {

    @NotEmpty
    private String name;

    private Long parentId;
}
