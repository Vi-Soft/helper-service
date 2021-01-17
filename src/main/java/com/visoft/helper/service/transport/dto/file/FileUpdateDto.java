package com.visoft.helper.service.transport.dto.file;

import com.visoft.helper.service.transport.dto.ordernumber.OrderNumberDto;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class FileUpdateDto extends OrderNumberDto {

    @NotEmpty
    @NotNull
    private String pathEn;

    @NotEmpty
    @NotNull
    private String pathRu;

    @NotEmpty
    @NotNull
    private String pathHe;
}
