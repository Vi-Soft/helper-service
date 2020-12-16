package com.visoft.helper.service.facade.folder;

import com.visoft.helper.service.exception.folder.FolderAlreadyExistsException;
import com.visoft.helper.service.persistance.entity.Application;
import com.visoft.helper.service.persistance.entity.Folder;
import com.visoft.helper.service.service.folder.FolderService;
import com.visoft.helper.service.service.ordernumber.OrderNumberService;
import com.visoft.helper.service.transport.dto.folder.FolderCreateDto;
import com.visoft.helper.service.transport.dto.folder.FolderOutcomeDto;
import com.visoft.helper.service.transport.dto.folder.FolderUpdateDto;
import com.visoft.helper.service.transport.mapper.FolderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FolderFacadeImpl implements FolderFacade {

    private final FolderService folderService;
    private final FolderMapper folderMapper;
    private final OrderNumberService orderNumberService;

    @Override
    public FolderOutcomeDto create(FolderCreateDto dto) {
        Folder folder = folderMapper.toEntity(dto);
        validateCreation(folder);
        recountOrderForCreation(folder);
        return folderMapper.toDto(
                folderService.save(folder)
        );
    }

    @Override
    public FolderOutcomeDto getById(Long id) {
        return folderMapper.toDto(
                getByIdUnsafe(id)
        );
    }

    @Override
    public void delete(Long id) {
        Folder folder = folderService.findByIdUnsafe(id);
        folderService.delete(folder);
    }

    @Override
    public Folder getByIdUnsafe(Long id) {
        return folderService.findByIdUnsafe(id);
    }

    @Override
    public FolderOutcomeDto update(Long id, FolderUpdateDto dto) {
        Folder folder = folderService.findByIdUnsafe(id);
        validateUpdate(folder, dto);
        recountOrderForUpdate(folder, dto);
        folderMapper.toEntity(dto, folder);
        return folderMapper.toDto(
                folderService.save(folder)
        );
    }

    private void validateUpdate(Folder folder, FolderUpdateDto dto) {
        if (!dto.getName().equals(folder.getName())) {
            existsFolderUnsafe(folder, dto);
        }
    }

    private void validateCreation(Folder folder) {
        existsFolderUnsafe(folder);
    }

    private void existsFolderUnsafe(Folder folder, FolderUpdateDto dto) {
        existsFolderUnsafe(
                folder.getApplication(),
                folder.getParent(),
                dto.getName()
        );
    }

    private void existsFolderUnsafe(Folder folder) {
        existsFolderUnsafe(
                folder.getApplication(),
                folder.getParent(),
                folder.getName()
        );
    }

    private void existsFolderUnsafe(
            Application application,
            Folder parent,
            String name
    ) {
        if (
                folderService.existsByApplicationAndParentAndName(
                        application,
                        parent,
                        name
                )
        ) {
            throw new FolderAlreadyExistsException();
        }
    }

    private void recountOrderForUpdate(
            Folder folder,
            FolderUpdateDto dto
    ) {
        int order = orderNumberService.recountFolderOrder(
                getFoldersSameLevel(folder.getApplication(), folder.getParent()),
                null,
                dto.getOrderNumber(),
                folder.getOrderNumber()
        );
        dto.setOrderNumber(order);
    }

    private void recountOrderForCreation(Folder folder) {
        orderNumberService.recountFolderOrder(
                getFoldersSameLevel(folder.getApplication(), folder.getParent()),
                folder,
                folder.getOrderNumber(),
                null
        );
    }

    private List<Folder> getFoldersSameLevel(Application application, Folder parent) {
        if (parent == null) {
            return application.getRootFolders();
        } else {
            return folderService.findAllByParent(parent);
        }
    }
}
