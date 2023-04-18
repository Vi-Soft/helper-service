package com.visoft.helper.service.service.file;

import com.visoft.helper.service.persistance.entity.file.File;

import java.util.List;

public interface FileService {

    File save(File file);

    File findByIdUnsafe(Long id);

    List<File> findAllByCopyIdAndIdNot(Long copyId, Long id);

    void delete(File file);

    Long getNextValFileCopySeq();
}
