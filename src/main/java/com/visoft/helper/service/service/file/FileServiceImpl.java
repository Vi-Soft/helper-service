package com.visoft.helper.service.service.file;

import com.visoft.helper.service.exception.file.FileNotFoundException;
import com.visoft.helper.service.persistance.entity.file.File;
import com.visoft.helper.service.persistance.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    @Transactional(readOnly = true)
    public File findByIdUnsafe(Long id) {
        return fileRepository.findById(id).orElseThrow(FileNotFoundException::new);
    }

    @Override
    @Transactional(readOnly = true)
    public List<File> findAllByCopyIdAndIdNot(Long copyId, Long id) {
        return fileRepository.findAllByCopyIdAndIdNot(copyId, id);
    }

    @Override
    public void delete(File file) {
        fileRepository.delete(file);
    }

    @Override
    public Long getNextValFileCopySeq() {
        return fileRepository.getNextValFileCopySeq();
    }
}
