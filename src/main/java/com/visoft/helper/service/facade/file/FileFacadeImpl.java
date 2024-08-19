package com.visoft.helper.service.facade.file;

import com.visoft.helper.service.persistance.entity.Folder;
import com.visoft.helper.service.service.folder.FolderService;
import com.visoft.helper.service.transport.dto.file.FileDto;
import com.visoft.helper.service.utils.FileSystem;
import com.visoft.helper.service.persistance.entity.file.File;
import com.visoft.helper.service.service.file.FileService;
import com.visoft.helper.service.service.ordernumber.OrderNumberService;
import com.visoft.helper.service.transport.dto.Language;
import com.visoft.helper.service.transport.dto.file.FileCreateDto;
import com.visoft.helper.service.transport.dto.file.FileOutcomeDto;
import com.visoft.helper.service.transport.dto.file.FileUpdateDto;
import com.visoft.helper.service.transport.mapper.FileMapper;
import com.visoft.helper.service.utils.UrlBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class FileFacadeImpl implements FileFacade {

    private final FileService fileService;
    private final FileMapper fileMapper;
    private final OrderNumberService orderNumberService;
    private final UrlBuilder urlBuilder;
    private final FileSystem fileSystem;
    private final FolderService folderService;

    @Override
    public FileOutcomeDto create(FileCreateDto dto) {
        return create(dto, true);
    }

    @Override
    public void copyFile(File file, Long folderId) {
        log.info("Copy file {}", file);
        create(
                fileMapper.toCreateDto(file, folderId),
                false
        );
    }

    @Override
    public FileOutcomeDto getById(Long id) {
        return fileMapper.toDto(
                fileService.findByIdUnsafe(id)
        );
    }

    @Override
    public FileOutcomeDto update(Long id, FileUpdateDto dto) {
        log.info("Update file {}, {}", id, dto);
        File file = fileService.findByIdUnsafe(id);
        FileOutcomeDto fileOutcomeDto = update(file, dto);
        log.info("File updated {}", fileOutcomeDto);

        final Long copyId = file.getCopyId();
        if (copyId != null) {
            fileService.findAllByCopyIdAndIdNot(copyId, file.getId()).forEach(fileCopy -> {
                log.info("Start update file copy {}", fileCopy.getId());
                update(fileCopy, dto);
                log.info("File copy updated {}", fileCopy);
            });
        }

        return fileOutcomeDto;
    }

    @Override
    public void delete(Long id) {
        fileService.delete(
                fileService.findByIdUnsafe(id)
        );
    }

    @Override
    public List<FileDto> upload(Language language, List<MultipartFile> files) {
        return list(language, files.stream().map(file -> fileSystem.write(language, file)).collect(Collectors.toList()));
    }

    @Override
    public List<FileDto> list(Language language) {
        return list(language, fileSystem.list(language));
    }

    private List<FileDto> list(Language language, List<String> fileNames) {
        log.error("language: " + language);
        log.error("fileNames: " + fileNames.size());
        return
                fileNames.stream()
                        .map(fileName ->
                                FileDto.builder()
                                        .fileNameWithExtension(fileName)
                                        .url(urlBuilder.generate(language, fileName))
                                        .build()
                        )
                        .collect(Collectors.toList());
    }

    public FileOutcomeDto update(File file, FileUpdateDto dto) {
        orderNumberService.recount(file, dto);
        fileMapper.toEntity(dto, file);
        return fileMapper.toDto(
            fileService.save(file)
        );
    }

    private FileOutcomeDto create(FileCreateDto dto, boolean enableRecount) {
        log.info("Create file {}", dto);
        File file = fileMapper.toEntity(dto);
        if (enableRecount) {
            orderNumberService.recount(file);
        }

        final Folder folder = file.getFolder();
        if (folder.getCopyId() != null) {
            final Long copyId = fileService.getNextValFileCopySeq();
            file.setCopyId(copyId);

            folderService.findAllByCopyIdAndIdNot(folder.getCopyId(), folder.getId()).forEach(folderCopy -> {
                dto.setApplicationId(folderCopy.getApplication().getId());
                dto.setFolderId(folderCopy.getId());
                createFileCopy(dto, enableRecount, copyId);
            });
        }

        FileOutcomeDto fileOutcomeDto = fileMapper.toDto(
                fileService.save(file)
        );
        log.info("File created {}", fileOutcomeDto);
        return fileOutcomeDto;
    }

    private void createFileCopy(FileCreateDto dto, boolean enableRecount, Long copyId) {
        log.info("Start create file copy {} for application {}", dto, dto.getApplicationId());
        File fileCopy = fileMapper.toEntity(dto);
        fileCopy.setCopyId(copyId);

        if (enableRecount) {
            orderNumberService.recount(fileCopy);
        }

        fileService.save(fileCopy);
        log.info("File copy created {}", fileCopy);
    }
}
