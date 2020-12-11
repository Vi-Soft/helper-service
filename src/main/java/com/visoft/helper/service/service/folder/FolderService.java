package com.visoft.helper.service.service.folder;

import com.visoft.helper.service.persistance.entity.Folder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FolderService {

    Folder save(Folder toDto);

    boolean existsByApplicationAndParentAndName(
            Long applicationId,
            Long parentId,
            String name
    );

    @Transactional(readOnly = true)
    boolean existsIdNotAndByApplicationAndParentAndName(
            Long id,
            Long applicationId,
            Long parentId,
            String name
    );

    Folder findByIdUnsafe(Long id);

    List<Folder> findAllByParent(Folder parent);
}
