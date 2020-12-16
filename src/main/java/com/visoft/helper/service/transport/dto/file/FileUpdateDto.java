package com.visoft.helper.service.transport.dto.file;

import com.visoft.helper.service.transport.dto.OrderNumberDto;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class FileUpdateDto extends OrderNumberDto {

    @NotEmpty
    private String name;

    @NotEmpty
    private String path;
}
