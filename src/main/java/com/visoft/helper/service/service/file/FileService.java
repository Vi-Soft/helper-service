package com.visoft.helper.service.service.file;

import com.visoft.helper.service.persistance.entity.file.File;

public interface FileService {

    File save(File file);

    File findByIdUnsafe(Long id);

    void delete(File file);
}
