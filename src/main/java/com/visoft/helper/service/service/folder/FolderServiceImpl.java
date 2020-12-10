package com.visoft.helper.service.service.folder;

import com.visoft.helper.service.persistance.entity.Application;
import com.visoft.helper.service.persistance.entity.Folder;
import com.visoft.helper.service.persistance.repository.FolderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class FolderServiceImpl implements FolderService {

    private final FolderRepository folderRepository;

    @Override
    public Folder create(Folder folder) {
        return folderRepository.save(folder);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsApplicationRootFolder(Application application) {
        return folderRepository.existsByApplicationAndParentIsNull(application);
    }
}
