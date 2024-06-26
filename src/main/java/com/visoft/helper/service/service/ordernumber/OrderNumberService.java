package com.visoft.helper.service.service.ordernumber;

import com.visoft.helper.service.persistance.entity.Folder;
import com.visoft.helper.service.persistance.entity.OrderNumber;
import com.visoft.helper.service.persistance.entity.file.File;
import com.visoft.helper.service.transport.dto.file.FileUpdateDto;
import com.visoft.helper.service.transport.dto.folder.FolderUpdateDto;

import java.util.List;

public interface OrderNumberService {

    void recount(Folder folder);

    void recount(Folder folder, FolderUpdateDto dto);

    void recount(File file);

    void recount(File file, FileUpdateDto dto);

    List<OrderNumber> getSortedByOrderNumberChildrenCommonOrderNumbers(Folder parentFolder);
}
