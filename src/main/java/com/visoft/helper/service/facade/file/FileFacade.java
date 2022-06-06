package com.visoft.helper.service.facade.file;

import com.visoft.helper.service.persistance.entity.file.File;
import com.visoft.helper.service.transport.dto.Language;
import com.visoft.helper.service.transport.dto.file.FileCreateDto;
import com.visoft.helper.service.transport.dto.file.FileDto;
import com.visoft.helper.service.transport.dto.file.FileOutcomeDto;
import com.visoft.helper.service.transport.dto.file.FileUpdateDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileFacade {

    FileOutcomeDto create(FileCreateDto dto);

    void copyFile(File file, Long folderId);

    FileOutcomeDto getById(Long id);

    FileOutcomeDto update(Long id, FileUpdateDto dto);

    void delete(Long id);

    String upload(Language language, MultipartFile file);

    List<FileDto> list(Language language);
}
