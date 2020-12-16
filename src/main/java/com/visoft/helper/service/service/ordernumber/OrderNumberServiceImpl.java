package com.visoft.helper.service.service.ordernumber;

import com.visoft.helper.service.persistance.entity.File;
import com.visoft.helper.service.persistance.entity.Folder;
import com.visoft.helper.service.persistance.entity.OrderNumberEntity;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class OrderNumberServiceImpl implements OrderNumberService {

    private static void setCorrectOrder(List<? extends OrderNumberEntity> collect) {
        for (int i = 0; i < collect.size(); i++) {
            collect.get(i).setOrderNumber(i);
        }
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

    private void sortByOrder(List<? extends OrderNumberEntity> collection) {
        collection.sort(Comparator.comparingInt(OrderNumberEntity::getOrderNumber));
    }
}
