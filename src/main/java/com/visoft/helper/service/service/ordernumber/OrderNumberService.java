package com.visoft.helper.service.service.ordernumber;

import com.visoft.helper.service.persistance.entity.File;
import com.visoft.helper.service.persistance.entity.Folder;

import java.util.List;

public interface OrderNumberService {

    int recountFileOrder(
            List<File> fileInSameFolder,
            File file,
            int order,
            Integer previousOrder
    );

    int recountFolderOrder(
            List<Folder> folderInSameFolder,
            Folder folder,
            int order,
            Integer previousOrder
    );
}
