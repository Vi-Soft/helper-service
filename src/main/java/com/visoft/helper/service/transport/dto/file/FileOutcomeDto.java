package com.visoft.helper.service.transport.dto.file;

import com.visoft.helper.service.transport.dto.OrderNumberIdDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileOutcomeDto extends OrderNumberIdDto {

    private String pathEn;
    private String pathRu;
    private String pathHe;
    private Long folderId;
}
