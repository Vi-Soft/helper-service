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
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Setter(onMethod_ = @Autowired)
public class FolderFacadeImpl implements FolderFacade {

    private FolderService folderService;
    private FolderMapper folderMapper;
    private OrderNumberService orderNumberService;

    @Override
    public FolderOutcomeDto create(FolderCreateDto dto) {
        return create(dto, true);
    }

    @Override
    public FolderOutcomeDto createWithoutReorder(FolderCreateDto dto) {
        return create(dto, false);
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
    public FolderOutcomeDto update(Long id, FolderUpdateDto dto) {
        Folder folder = folderService.findByIdUnsafe(id);
        validateUpdate(folder, dto);
        orderNumberService.recount(folder, dto);
        folderMapper.toEntity(dto, folder);
        return folderMapper.toDto(
                folderService.save(folder)
        );
    }

    private Folder getByIdUnsafe(Long id) {
        return folderService.findByIdUnsafe(id);
    }

    private FolderOutcomeDto create(FolderCreateDto dto, boolean enableRecount) {
        Folder folder = folderMapper.toEntity(dto);
        validateCreation(folder);
        if (enableRecount) {
            orderNumberService.recount(folder);
        }
        return folderMapper.toDto(
                folderService.save(folder)
        );
    }

    private void validateUpdate(Folder folder, FolderUpdateDto dto) {
        if (!(dto.getNameEn().equals(folder.getNameEn()) &&
                dto.getNameHe().equals(folder.getNameHe()) &&
                dto.getNameRu().equals(folder.getNameRu()))) {
            existsFolderIdNotUnsafe(folder, dto);
        }
    }

    private void validateCreation(Folder folder) {
        existsFolderUnsafe(folder);
    }

    private void existsFolderIdNotUnsafe(Folder folder, FolderUpdateDto dto) {
        existsFolderIdNotUnsafe(
                folder.getId(),
                folder.getApplication(),
                folder.getParent(),
                dto.getNameEn(),
                dto.getNameRu(),
                dto.getNameHe()
        );
    }

    private void existsFolderUnsafe(Folder folder) {
        existsFolderUnsafe(
                folder.getApplication(),
                folder.getParent(),
                folder.getNameEn(),
                folder.getNameRu(),
                folder.getNameHe()

        );
    }

    private void existsFolderIdNotUnsafe(
            Long id,
            Application application,
            Folder parent,
            String nameEn,
            String nameRu,
            String nameHe
    ) {
        boolean existsByNameEn = folderService.existsByIdNotAndApplicationAndParentAndNameEn(id, application, parent, nameEn);
        boolean existsByNameRu = folderService.existsByIdNotAndApplicationAndParentAndNameRu(id, application, parent, nameRu);
        boolean existsByNameHe = folderService.existsByIdNotAndApplicationAndParentAndNameHe(id, application, parent, nameHe);

        if (existsByNameEn || existsByNameRu || existsByNameHe) {
            throw new FolderAlreadyExistsException();
        }
    }

    private void existsFolderUnsafe(
            Application application,
            Folder parent,
            String nameEn,
            String nameRu,
            String nameHe
    ) {
        boolean existsByNameEn = folderService.existsByApplicationAndParentAndNameEn(application, parent, nameEn);
        boolean existsByNameRu = folderService.existsByApplicationAndParentAndNameRu(application, parent, nameRu);
        boolean existsByNameHe = folderService.existsByApplicationAndParentAndNameHe(application, parent, nameHe);

        if (existsByNameEn || existsByNameRu || existsByNameHe) {
            throw new FolderAlreadyExistsException();
        }
    }
}