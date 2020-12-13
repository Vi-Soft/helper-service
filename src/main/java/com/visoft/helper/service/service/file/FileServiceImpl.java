package com.visoft.helper.service.service.file;

import com.visoft.helper.service.exception.file.FileNotFoundException;
import com.visoft.helper.service.persistance.entity.File;
import com.visoft.helper.service.persistance.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;

    @Override
    public File save(File file) {
        return fileRepository.save(file);
    }

    @Override
    public File findByIdUnsafe(Long id) {
        return fileRepository.findById(id).orElseThrow(FileNotFoundException::new);
    }
}
