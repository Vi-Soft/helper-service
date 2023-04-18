package com.visoft.helper.service.persistance.repository;

import com.visoft.helper.service.persistance.entity.file.File;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FileRepository extends GeneralRepository<File> {

    @Query(value = "SELECT nextval('file_copy_seq')", nativeQuery = true)
    Long getNextValFileCopySeq();

    List<File> findAllByCopyIdAndIdNot(Long copyId, Long id);
}
