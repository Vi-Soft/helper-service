package com.visoft.helper.service.service.file;

import com.visoft.helper.service.persistance.entity.File;
import com.visoft.helper.service.transport.dto.file.FileOutcomeDto;

public interface FileService {

    File save(File file);

    File findByIdUnsafe(Long id);
}
