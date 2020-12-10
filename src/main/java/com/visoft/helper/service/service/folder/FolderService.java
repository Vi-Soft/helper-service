package com.visoft.helper.service.service.folder;

import com.visoft.helper.service.persistance.entity.Application;
import com.visoft.helper.service.persistance.entity.Folder;

import java.util.List;

public interface FolderService {

    Folder create(Folder toDto);

    boolean existsByApplicationAndParentAndName(Application application,
                                                Folder parent,
                                                String name
    );

    Folder findByIdUnsafe(Long id);

    List<Folder> findAllByParent(Folder parent);
}
