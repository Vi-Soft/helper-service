package com.visoft.helper.service.persistance.repository;

import com.visoft.helper.service.persistance.entity.Folder;

import java.util.List;

public interface FolderRepository extends GeneralRepository<Folder> {

    boolean existsByApplicationIdAndParentIdAndName(
            Long applicationId,
            Long parentId,
            String name
    );

    boolean existsByIdNotAndApplicationIdAndParentIdAndName(
            Long id,
            Long applicationId,
            Long parentId,
            String name
    );

    List<Folder> findAllByParent(Folder parent);
}
