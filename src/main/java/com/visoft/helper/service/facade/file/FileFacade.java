package com.visoft.helper.service.facade.file;

import com.visoft.helper.service.persistance.entity.file.File;
import com.visoft.helper.service.transport.dto.file.FileCreateDto;
import com.visoft.helper.service.transport.dto.file.FileOutcomeDto;
import com.visoft.helper.service.transport.dto.file.FileUpdateDto;

public interface FileFacade {

    FileOutcomeDto create(FileCreateDto dto);

    void copyFile(File file, Long folderId);

    FileOutcomeDto getById(Long id);

    FileOutcomeDto update(Long id, FileUpdateDto dto);

    void delete(Long id);
}
