package com.visoft.helper.service.service.folder;

import com.visoft.helper.service.exception.folder.FolderNotFoundException;
import com.visoft.helper.service.persistance.entity.Folder;
import com.visoft.helper.service.persistance.repository.FolderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FolderServiceImpl implements FolderService {

    private final FolderRepository folderRepository;

    @Override
    public Folder save(Folder folder) {
        return folderRepository.save(folder);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByApplicationAndParentAndName(
            Long applicationId,
            Long parentId,
            String name
    ) {
        return folderRepository.existsByApplicationIdAndParentIdAndName(applicationId, parentId, name);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsIdNotAndByApplicationAndParentAndName(
            Long id,
            Long applicationId,
            Long parentId,
            String name
    ) {
        return folderRepository.existsByIdNotAndApplicationIdAndParentIdAndName(
                id,
                applicationId,
                parentId,
                name
        );
    }

    @Override
    @Transactional(readOnly = true)
    public Folder findByIdUnsafe(Long id) {
        return folderRepository.findById(id).orElseThrow(FolderNotFoundException::new);
    }

    @Override
    public List<Folder> findAllByParent(Folder parent) {
        return folderRepository.findAllByParent(parent);
    }
}
