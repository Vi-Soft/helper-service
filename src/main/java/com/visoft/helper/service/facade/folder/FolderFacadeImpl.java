package com.visoft.helper.service.facade.folder;

import com.visoft.helper.service.exception.BadRequestException;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Component
@Setter(onMethod_ = @Autowired)
@Slf4j
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
        log.info("Start update folder {}", id);
        Folder folder = folderService.findByIdUnsafe(id);
        FolderOutcomeDto folderOutcomeDto = update(folder, dto);
        log.info("Folder updated {}", folderOutcomeDto);

        final Long copyId = folder.getCopyId();
        if (copyId != null) {
            folderService.findAllByCopyIdAndIdNot(copyId, folder.getId()).forEach(folderCopy -> {
                try {
                    log.info("Start update folder copy {}", folderCopy.getId());
                    update(folder, dto);
                    log.info("Folder copy updated {}", folderCopy);
                } catch (BadRequestException e) {
                    e.printStackTrace();
                    log.warn("Couldn't update folder copy {}", folderCopy);
                }
            });
        }

        return folderOutcomeDto;
    }

    private Folder getByIdUnsafe(Long id) {
        return folderService.findByIdUnsafe(id);
    }

    private FolderOutcomeDto update(Folder folder, FolderUpdateDto dto) {
        validateUpdate(folder, dto);
        orderNumberService.recount(folder, dto);
        folderMapper.toEntity(dto, folder);
        return folderMapper.toDto(
            folderService.save(folder)
        );
    }

    private FolderOutcomeDto create(FolderCreateDto dto, boolean enableRecount) {
        log.info("Start create folder {}. Recount enabled: {}", dto, enableRecount);
        Folder folder = folderMapper.toEntity(dto);
        validateCreation(folder);
        if (enableRecount) {
            orderNumberService.recount(folder);
        }

        final Folder parent = folder.getParent();
        final List<Long> additionApplicationIds = dto.getAdditionApplicationIds();
        final boolean isParentHasCopies = parent != null && parent.getCopyId() != null;
        final boolean isAdditionalApplicationsNotEmpty = !CollectionUtils.isEmpty(additionApplicationIds);

        if (isParentHasCopies || isAdditionalApplicationsNotEmpty) {
            final Long copyId = folderService.getNextValFolderCopySeq();
            folder.setCopyId(copyId);

            if (isParentHasCopies) {
                folderService.findAllByCopyIdAndIdNot(parent.getCopyId(), parent.getId()).forEach(parentCopy -> {
                    dto.setApplicationId(parentCopy.getApplication().getId());
                    dto.setParentId(parentCopy.getId());
                    createFolderCopy(dto, enableRecount, copyId);
                });
            } else {
                additionApplicationIds.forEach(applicationId -> {
                    dto.setApplicationId(applicationId);
                    createFolderCopy(dto, enableRecount, copyId);
                });
            }
        }

        FolderOutcomeDto folderOutcomeDto = folderMapper.toDto(
                folderService.save(folder)
        );
        log.info("Folder created {}", folderOutcomeDto);
        return folderOutcomeDto;
    }

    private void createFolderCopy(FolderCreateDto dto, boolean enableRecount, Long copyId) {
        try {
            log.info("Start create folder copy {} for application {}", dto, dto.getApplicationId());
            Folder folderCopy = folderMapper.toEntity(dto);
            folderCopy.setCopyId(copyId);
            validateCreation(folderCopy);

            if (enableRecount) {
                orderNumberService.recount(folderCopy);
            }

            folderService.save(folderCopy);
            log.info("Folder copy created {}", folderCopy);
        } catch (BadRequestException e) {
            e.printStackTrace();
            log.warn("Couldn't create folder copy for application {}", dto.getApplicationId());
        }
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
            isExistFolderByNameLog(existsByNameEn, existsByNameRu, existsByNameHe, nameEn, nameRu, nameHe);
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
            isExistFolderByNameLog(existsByNameEn, existsByNameRu, existsByNameHe, nameEn, nameRu, nameHe);
            throw new FolderAlreadyExistsException();
        }
    }

    private void isExistFolderByNameLog(boolean existsByNameEn,
                                        boolean existsByNameRu,
                                        boolean existsByNameHe,
                                        String nameEn,
                                        String nameRu,
                                        String nameHe
    ) {
        if (existsByNameEn)
            log.info("Folder EN is already exists: {}", nameEn);
        if (existsByNameRu)
            log.info("Folder RU is already exists: {}", nameRu);
        if (existsByNameHe)
            log.info("Folder HE is already exists: {}", nameHe);
    }
}
