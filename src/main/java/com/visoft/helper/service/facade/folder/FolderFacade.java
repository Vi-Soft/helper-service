package com.visoft.helper.service.facade.folder;

import com.visoft.helper.service.persistance.entity.Folder;
import com.visoft.helper.service.transport.dto.folder.FolderCreateDto;
import com.visoft.helper.service.transport.dto.folder.FolderOutcomeDto;

public interface FolderFacade {

    FolderOutcomeDto create(FolderCreateDto dto);

    Folder getByIdUnsafe(Long id);

    FolderOutcomeDto getById(Long id);
}
