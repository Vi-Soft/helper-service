package com.visoft.helper.service.facade.file;

import com.visoft.helper.service.transport.dto.file.FileCreateDto;
import com.visoft.helper.service.transport.dto.file.FileOutcomeDto;

public interface FileFacade {

    FileOutcomeDto create(FileCreateDto dto);
}
