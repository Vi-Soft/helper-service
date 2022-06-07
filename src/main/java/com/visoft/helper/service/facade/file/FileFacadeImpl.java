package com.visoft.helper.service.facade.file;

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
        orderNumberService.recount(file, dto);
        fileMapper.toEntity(dto, file);
        FileOutcomeDto fileOutcomeDto = fileMapper.toDto(
                fileService.save(file)
        );
        log.info("File updated {}", fileOutcomeDto);
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

    private FileOutcomeDto create(FileCreateDto dto, boolean enableRecount) {
        log.info("Create folder {}", dto);
        File file = fileMapper.toEntity(dto);
        if (enableRecount) {
            orderNumberService.recount(file);
        }
        FileOutcomeDto fileOutcomeDto = fileMapper.toDto(
                fileService.save(file)
        );
        log.info("Folder created {}", fileOutcomeDto);
        return fileOutcomeDto;
    }
}
