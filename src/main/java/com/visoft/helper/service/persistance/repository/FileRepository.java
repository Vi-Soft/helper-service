package com.visoft.helper.service.persistance.repository;

import com.visoft.helper.service.persistance.entity.Folder;
import com.visoft.helper.service.persistance.entity.file.File;

import java.util.List;

public interface FileRepository extends GeneralRepository<File> {

    List<File> findAllByFolder(Folder folder);
}
