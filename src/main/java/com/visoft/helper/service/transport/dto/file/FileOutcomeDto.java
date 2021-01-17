package com.visoft.helper.service.transport.dto.file;

import com.visoft.helper.service.transport.dto.ordernumber.OrderNumberIdDto;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class FileOutcomeDto extends OrderNumberIdDto {

    @NotNull
    private String pathEn;

    @NotNull
    private String pathRu;

    @NotNull
    private String pathHe;

    @NotNull
    private Long folderId;
}
