package com.visoft.helper.service.service.folder;

import com.visoft.helper.service.persistance.entity.Application;
import com.visoft.helper.service.persistance.entity.Folder;

public interface FolderService {

    Folder save(Folder toDto);

    Folder findByIdUnsafe(Long id);

    boolean existsByApplicationAndParentAndNameEn(
            Application application,
            Folder parent,
            String nameEn
    );

    boolean existsByApplicationAndParentAndNameRu(
            Application application,
            Folder parent,
            String nameRu
    );

    boolean existsByApplicationAndParentAndNameHe(
            Application application,
            Folder parent,
            String nameHe
    );

    boolean existsByIdNotAndApplicationAndParentAndNameEn(
            Long id,
            Application application,
            Folder parent,
            String nameEn
    );

    boolean existsByIdNotAndApplicationAndParentAndNameRu(
            Long id,
            Application application,
            Folder parent,
            String nameRu
    );

    boolean existsByIdNotAndApplicationAndParentAndNameHe(
            Long id,
            Application application,
            Folder parent,
            String nameHe
    );

    void delete(Folder folder);
}
