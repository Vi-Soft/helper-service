package com.visoft.helper.service.facade.folder;

import com.visoft.helper.service.exception.folder.FolderAlreadyExistsException;
import com.visoft.helper.service.persistance.entity.Folder;
import com.visoft.helper.service.service.folder.FolderService;
import com.visoft.helper.service.transport.dto.folder.FolderCreateDto;
import com.visoft.helper.service.transport.dto.folder.FolderOutcomeDto;
import com.visoft.helper.service.transport.mapper.FolderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FolderFacadeImpl implements FolderFacade {

    private final FolderService folderService;
    private final FolderMapper folderMapper;

    @Override
    public FolderOutcomeDto create(FolderCreateDto dto) {
        Folder folder = folderMapper.toEntity(dto);
        validateCreation(folder);
        recountOrder(folder);
        return folderMapper.toDto(
                folderService.create(folder)
        );
    }

    @Override
    public Folder getByIdUnsafe(Long id) {
        return folderService.findByIdUnsafe(id);
    }

    @Override
    public FolderOutcomeDto getById(Long id) {
        return folderMapper.toDto(folderService.findByIdUnsafe(id));
    }

    private void validateCreation(Folder folder) {
        existsApplicationRootFolder(folder);
    }

    private void existsApplicationRootFolder(Folder folder) {
        if (folderService.existsByApplicationAndParentAndName(
                folder.getApplication(),
                folder.getParent(),
                folder.getName()
        )
        ) {
            throw new FolderAlreadyExistsException();
        }
    }

    private void recountOrder(Folder folder) {
        List<Folder> collect = getRootFoldersSortedByOrder(folder);
        if (folder.getParent() != null) {
            collect = getFoldersSortedByOrder(folder);
        }

        int size = collect.size();
        if (folder.getFolderOrder() < size) {
            for (long i = folder.getFolderOrder(); i < size; i++) {
                collect.get((int) i).setFolderOrder(i + 1);
            }
        }
        if (folder.getFolderOrder() > size) {
            folder.setFolderOrder(size);
        }
    }

    private List<Folder> getFoldersSortedByOrder(Folder folder) {
        List<Folder> allByParent = folderService.findAllByParent(folder.getParent());
        sortByOrder(allByParent);
        return allByParent;
    }

    private List<Folder> getRootFoldersSortedByOrder(Folder folder) {
        List<Folder> rootFolders = folder.getApplication().getRootFolders();
        sortByOrder(rootFolders);
        return rootFolders;
    }

    private void sortByOrder(List<Folder> folders) {
        folders.sort(Comparator.comparingLong(Folder::getFolderOrder));
    }
}
