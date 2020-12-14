package com.visoft.helper.service.facade.file;

import com.visoft.helper.service.persistance.entity.File;
import com.visoft.helper.service.persistance.entity.Folder;
import com.visoft.helper.service.service.file.FileService;
import com.visoft.helper.service.transport.dto.file.FileCreateDto;
import com.visoft.helper.service.transport.dto.file.FileOutcomeDto;
import com.visoft.helper.service.transport.mapper.FileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FileFacadeImpl implements FileFacade {

    private final FileService fileService;
    private final FileMapper fileMapper;

    @Override
    public FileOutcomeDto create(FileCreateDto dto) {
        File file = fileMapper.toEntity(dto);
        recountOrderForCreation(file);
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

    private void recountOrderForCreation(File file) {
        recountOrder(
                file.getFolder(),
                file,
                file.getOrderNumber(),
                null
        );
    }

    private void recountOrder(
            Folder folder,
            File file,
            int order,
            Integer previousOrder
    ) {
        List<File> collect = getFilesSortedByOrder(folder);
        if (previousOrder != null) {
            collect.remove(file);
        }
        if (order > collect.size()) {
            order = collect.size();
        }
        collect.add(order, file);
        setCorrectOrder(collect);
    }

    private void setCorrectOrder(List<File> collect) {
        for (int i = 0; i < collect.size(); i++) {
            collect.get(i).setOrderNumber(i);
        }
    }

    private List<File> getFilesSortedByOrder(Folder folder) {
        List<File> allByParent = fileService.findAllByFolder(folder);
        sortByOrder(allByParent);
        return allByParent;
    }

    private void sortByOrder(List<File> folders) {
        folders.sort(Comparator.comparingInt(File::getOrderNumber));
    }


}
