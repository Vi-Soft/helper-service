package com.visoft.helper.service.persistance.repository;

import com.visoft.helper.service.persistance.entity.Application;
import com.visoft.helper.service.persistance.entity.Folder;

public interface FolderRepository extends GeneralRepository<Folder> {

    boolean existsByApplicationAndParentIsNull(Application application);
}
