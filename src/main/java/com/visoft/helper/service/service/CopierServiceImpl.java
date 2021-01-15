package com.visoft.helper.service.service;

import com.visoft.helper.service.exception.InternalServerError;
import com.visoft.helper.service.facade.application.ApplicationFacade;
import com.visoft.helper.service.facade.file.FileFacade;
import com.visoft.helper.service.facade.folder.FolderFacade;
import com.visoft.helper.service.persistance.entity.Application;
import com.visoft.helper.service.persistance.entity.Folder;
import com.visoft.helper.service.persistance.entity.file.File;
import com.visoft.helper.service.service.application.ApplicationService;
import com.visoft.helper.service.service.ordernumber.OrderNumberService;
import com.visoft.helper.service.transport.dto.application.ApplicationCopyDto;
import com.visoft.helper.service.transport.dto.application.ApplicationOutcomeDto;
import com.visoft.helper.service.transport.dto.folder.FolderCreateDto;
import com.visoft.helper.service.transport.dto.folder.FolderOutcomeDto;
import com.visoft.helper.service.transport.mapper.FolderMapper;
import com.visoft.helper.service.transport.mapper.application.ApplicationMapper;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Setter(onMethod_ = @Autowired)
public class CopierServiceImpl implements CopierService {

    private ApplicationService applicationService;
    private ApplicationMapper applicationMapper;
    private ApplicationFacade applicationFacade;

    private FolderMapper folderMapper;
    private FolderFacade folderFacade;

    private OrderNumberService orderNumberService;

    private FileFacade fileFacade;

    @Override
    public void copyApplication(Long id, ApplicationCopyDto dto) {
        Application application = applicationService.findByIdUnsafe(id);
        ApplicationOutcomeDto applicationOutcomeDto = applicationFacade.create(
                applicationMapper.toCreateDto(dto)
        );
        copyRootFolders(
                application.getRootFolders(),
                applicationOutcomeDto.getId()
        );
    }

    public void copyRootFolders(List<Folder> rootFolders, Long applicationId) {
        rootFolders.forEach(
                folder -> {
                    Long folderOutcomeDtoId = createForCopy(
                            folderMapper.toCreateDto(folder, applicationId, null)
                    ).getId();
                    copyFilesAndFolders(
                            folder,
                            folderOutcomeDtoId,
                            applicationId
                    );
                }
        );
    }

    private FolderOutcomeDto createForCopy(FolderCreateDto dto) {
        return folderFacade.createWithoutReorder(dto);
    }

    private void copyFilesAndFolders(Folder copyFolder, Long copiedFolderId, Long applicationId) {
        orderNumberService.getSortedByOrderNumberChildrenCommonOrderNumbers(copyFolder).forEach(
                orderNumber -> {
                    if (orderNumber instanceof Folder) {
                        copyFolderAndChildrenFoldersAndFiles(
                                copiedFolderId,
                                applicationId,
                                (Folder) orderNumber
                        );
                    } else if (orderNumber instanceof File) {
                        copyFile(
                                copiedFolderId,
                                (File) orderNumber
                        );
                    } else {
                        throw new InternalServerError("Order number has unknown children");
                    }
                }
        );
    }

    private void copyFolderAndChildrenFoldersAndFiles(
            Long copiedFolderId,
            Long applicationId,
            Folder orderNumber
    ) {
        Long outcomeDtoId = copyFolder(
                copiedFolderId,
                applicationId,
                orderNumber
        );
        copyChildrenFolderAndFiles(
                applicationId,
                orderNumber,
                outcomeDtoId
        );
    }

    private Long copyFolder(Long copiedFolderId, Long applicationId, Folder folder) {
        return createForCopy(
                folderMapper.toCreateDto(
                        folder,
                        applicationId,
                        copiedFolderId
                )
        ).getId();
    }

    private void copyChildrenFolderAndFiles(Long applicationId, Folder folder, Long outcomeDtoId) {
        if (!folder.getChildren().isEmpty() || !folder.getFiles().isEmpty()) {
            copyFilesAndFolders(
                    folder,
                    outcomeDtoId,
                    applicationId
            );
        }
    }

    private void copyFile(Long copiedFolderId, File orderNumber) {
        fileFacade.copyFile(
                orderNumber,
                copiedFolderId
        );
    }
}
