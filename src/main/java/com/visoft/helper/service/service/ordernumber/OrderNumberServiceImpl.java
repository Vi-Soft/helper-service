package com.visoft.helper.service.service.ordernumber;

import com.visoft.helper.service.persistance.entity.File;
import com.visoft.helper.service.persistance.entity.Folder;
import com.visoft.helper.service.persistance.entity.OrderNumberEntity;
import com.visoft.helper.service.service.folder.FolderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderNumberServiceImpl implements OrderNumberService {

    private final FolderService folderService;

//    public void recountCreateFolder(Folder folder){
//        if (folder.getParent()==null){
//            List<Folder> rootFolders = folder.getApplication().getRootFolders();
//            sortByOrder(rootFolders);
//            if (rootFolders.isEmpty()){
//               folder.setOrderNumber(0);
//            }else {
//                int size = rootFolders.size();
//                if (folder.getOrderNumber()>size) {
//                    folder.setOrderNumber(size);
//                }else{
//                    rootFolders.add(folder.getOrderNumber(), folder);
//                    setCorrectOrder(rootFolders);
//                }
//            }
//        }else {
//            List<Folder> foldersInSameOrder = folderService.findAllByParent(folder.getParent());
//            if (foldersInSameOrder)
//        }
//    }


    @Override
    public int recountFolderOrder(
            List<Folder> folderInSameFolder,
            Folder folder,
            int order,
            Integer previousOrder
    ) {
        sortByOrder(folderInSameFolder);
        if (folder == null) {
            folder = folderInSameFolder.get(previousOrder);
        }
        if (previousOrder != null) {
            folderInSameFolder.remove(folder);
        }
        if (order > folderInSameFolder.size()) {
            order = folderInSameFolder.size();
        }
        folderInSameFolder.add(order, folder);
        setCorrectOrder(folderInSameFolder);
        return order;
    }

    @Override
    public int recountFileOrder(
            List<File> fileInSameFolder,
            File file,
            int order,
            Integer previousOrder
    ) {
        sortByOrder(fileInSameFolder);
        if (previousOrder != null) {
            fileInSameFolder.remove(file);
        }
        if (order > fileInSameFolder.size()) {
            order = fileInSameFolder.size();
        }
        fileInSameFolder.add(order, file);
        setCorrectOrder(fileInSameFolder);
        return order;
    }

    private void sortByOrder(List<? extends OrderNumberEntity> collection) {
        collection.sort(Comparator.comparingInt(OrderNumberEntity::getOrderNumber));
    }

    private void setCorrectOrder(List<? extends OrderNumberEntity> collect) {
        for (int i = 0; i < collect.size(); i++) {
            collect.get(i).setOrderNumber(i);
        }
    }
}
