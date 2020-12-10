package com.visoft.helper.service.facade.folder;

import com.visoft.helper.service.exception.application.ApplicationAlreadyHasRootFolderException;
import com.visoft.helper.service.persistance.entity.Folder;
import com.visoft.helper.service.service.folder.FolderService;
import com.visoft.helper.service.transport.dto.folder.FolderOutcomeDto;
import com.visoft.helper.service.transport.dto.folder.FolderRootCreateDto;
import com.visoft.helper.service.transport.mapper.FolderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FolderFacadeImpl implements FolderFacade {

    private final FolderService folderService;
    private final FolderMapper folderMapper;

    @Override
    public FolderOutcomeDto createRoot(FolderRootCreateDto dto) {
        Folder folder = folderMapper.toEntity(dto);
        validateCreation(folder);
        return folderMapper.toDto(
                folderService.create(folder)
        );
    }

    private void validateCreation(Folder folder) {
        existsApplicationRootFolder(folder);
    }

    private void existsApplicationRootFolder(Folder folder) {
        if (folderService.existsApplicationRootFolder(folder.getApplication())) {
            throw new ApplicationAlreadyHasRootFolderException();
        }
    }
}
