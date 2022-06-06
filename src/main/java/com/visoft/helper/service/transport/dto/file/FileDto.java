package com.visoft.helper.service.transport.dto.file;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FileDto {

    private String fileNameWithExtension;
    private String url;
}
