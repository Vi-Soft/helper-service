package com.visoft.helper.service.service.folder;

import com.visoft.helper.service.persistance.entity.Application;
import com.visoft.helper.service.persistance.entity.Folder;

public interface FolderService {

    Folder create(Folder toDto);

    boolean existsApplicationRootFolder(Application application);
}
