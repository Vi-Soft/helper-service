package com.visoft.helper.service.persistance.repository;

import com.visoft.helper.service.persistance.entity.Application;
import com.visoft.helper.service.persistance.entity.Folder;

import java.util.List;

public interface FolderRepository extends GeneralRepository<Folder> {

    boolean existsByApplicationAndParentAndName(
            Application application,
            Folder parent,
            String name
    );

    List<Folder> findAllByParent(Folder parent);
}
