package com.visoft.helper.service.service.ordernumber;

import com.visoft.helper.service.persistance.entity.File;
import com.visoft.helper.service.persistance.entity.Folder;
import com.visoft.helper.service.transport.dto.folder.FolderUpdateDto;

import java.util.List;

public interface OrderNumberService {

    int recountFileOrder(
            List<File> fileInSameFolder,
            File file,
            int order,
            Integer previousOrder
    );

    void recountCreateFolder(Folder folder);

    void recountUpdateFolder(Folder folder, FolderUpdateDto dto);

    int recountFolderOrder(
            List<Folder> folderInSameFolder,
            Folder folder,
            int order,
            Integer previousOrder
    );
}
