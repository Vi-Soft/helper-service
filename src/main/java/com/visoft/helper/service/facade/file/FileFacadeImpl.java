package com.visoft.helper.service.facade.file;

import com.visoft.helper.service.persistance.entity.File;
import com.visoft.helper.service.service.file.FileService;
import com.visoft.helper.service.service.ordernumber.OrderNumberService;
import com.visoft.helper.service.transport.dto.file.FileCreateDto;
import com.visoft.helper.service.transport.dto.file.FileOutcomeDto;
import com.visoft.helper.service.transport.dto.file.FileUpdateDto;
import com.visoft.helper.service.transport.mapper.FileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FileFacadeImpl implements FileFacade {

    private final FileService fileService;
    private final FileMapper fileMapper;
    private final OrderNumberService orderNumberService;

    @Override
    public FileOutcomeDto create(FileCreateDto dto) {
        File file = fileMapper.toEntity(dto);
        orderNumberService.recount(file);
        return fileMapper.toDto(
                fileService.save(file)
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
        File file = fileService.findByIdUnsafe(id);
        orderNumberService.recount(file, dto);
        fileMapper.toEntity(dto, file);
        return fileMapper.toDto(
                fileService.save(file)
        );
    }

    @Override
    public void delete(Long id) {
        fileService.delete(
                fileService.findByIdUnsafe(id)
        );
    }
}
