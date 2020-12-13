package com.visoft.helper.service.facade.file;

import com.visoft.helper.service.persistance.entity.File;
import com.visoft.helper.service.service.file.FileService;
import com.visoft.helper.service.transport.dto.file.FileCreateDto;
import com.visoft.helper.service.transport.dto.file.FileOutcomeDto;
import com.visoft.helper.service.transport.mapper.FileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FileFacadeImpl implements FileFacade {

    private final FileService fileService;
    private final FileMapper fileMapper;

    @Override
    public FileOutcomeDto create(FileCreateDto dto) {
        File file = fileMapper.toEntity(dto);
        return fileMapper.toDto(
                fileService.save(file)
        );
    }
}
