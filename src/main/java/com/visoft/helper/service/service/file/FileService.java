package com.visoft.helper.service.service.file;

import com.visoft.helper.service.persistance.entity.File;
import com.visoft.helper.service.persistance.entity.Folder;

import java.util.List;

public interface FileService {

    File save(File file);

    File findByIdUnsafe(Long id);

    List<File> findAllByFolder(Folder folder);
}
