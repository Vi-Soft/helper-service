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
import java.util.Objects;

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
        Folder parent = folder.getParent();
        if (!Objects.equals(dto.getParentId(), (parent == null ? null : parent.getId()))) {
            existsFolderUnsafe(
                    folder.getApplication().getId(),
                    dto
            );
        } else if (!dto.getName().equals(folder.getName())) {
            existsFolderInSameLevelUnsafe(folder, dto);
        }
    }

    private void validateCreation(Folder folder) {
        existsFolderUnsafe(folder);
    }

    private void existsFolderInSameLevelUnsafe(Folder folder, FolderUpdateDto dto) {
        if (folderService.existsIdNotAndByApplicationAndParentAndName(
                folder.getId(),
                folder.getApplication().getId(),
                dto.getParentId(),
                dto.getName()
        )
        ) {
            throw new FolderAlreadyExistsException();
        }
    }

    private void existsFolderUnsafe(Folder folder) {
        Folder parent = folder.getParent();
        existsFolderUnsafe(
                folder.getApplication().getId(),
                parent == null ? null : parent.getId(),
                folder.getName()
        );
    }

    private void existsFolderUnsafe(Long applicationId, FolderUpdateDto dto) {
        existsFolderUnsafe(
                applicationId,
                dto.getParentId(),
                dto.getName()
        );
    }

    private void existsFolderUnsafe(
            Long applicationId,
            Long parentId,
            String name
    ) {
        if (
                folderService.existsByApplicationAndParentAndName(
                        applicationId,
                        parentId,
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
        Folder parent = dto.getParentId() == null ? null : getByIdUnsafe(dto.getParentId());
        int order = orderNumberService.recountFolderOrder(
                getFoldersSameLevel(folder.getApplication(), parent),
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
