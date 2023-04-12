package com.visoft.helper.service.persistance.repository;

import com.visoft.helper.service.persistance.entity.Application;
import com.visoft.helper.service.persistance.entity.Folder;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FolderRepository extends GeneralRepository<Folder> {

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

    List<Folder> findAllByCopyIdAndIdNot(Long copyId, Long id);

    @Query(value = "SELECT nextval('folder_copy_seq')", nativeQuery = true)
    Long getNextValFolderCopySeq();
}
