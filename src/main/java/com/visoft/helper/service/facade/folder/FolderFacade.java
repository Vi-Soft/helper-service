package com.visoft.helper.service.facade.folder;

import com.visoft.helper.service.transport.dto.folder.FolderOutcomeDto;
import com.visoft.helper.service.transport.dto.folder.FolderRootCreateDto;

public interface FolderFacade {

    FolderOutcomeDto createRoot(FolderRootCreateDto dto);
}
