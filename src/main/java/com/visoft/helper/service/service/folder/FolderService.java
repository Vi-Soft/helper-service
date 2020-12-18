package com.visoft.helper.service.service.folder;

import com.visoft.helper.service.persistance.entity.Folder;

import java.util.List;

public interface FolderService {

    Folder save(Folder toDto);

    Folder findByIdUnsafe(Long id);

    List<Folder> findAllByParent(Folder parent);

    void delete(Folder folder);
}
