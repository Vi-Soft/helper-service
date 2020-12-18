package com.visoft.helper.service.persistance.repository;

import com.visoft.helper.service.persistance.entity.Folder;

import java.util.List;

public interface FolderRepository extends GeneralRepository<Folder> {

    List<Folder> findAllByParent(Folder parent);
}
