package com.visoft.helper.service.service.ordernumber;

import com.visoft.helper.service.persistance.entity.File;
import com.visoft.helper.service.persistance.entity.Folder;
import com.visoft.helper.service.persistance.entity.OrderNumberEntity;
import com.visoft.helper.service.transport.dto.OrderNumberDto;
import com.visoft.helper.service.transport.dto.file.FileUpdateDto;
import com.visoft.helper.service.transport.dto.folder.FolderUpdateDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class OrderNumberServiceImpl implements OrderNumberService {

    @Override
    public void recount(Folder folder) {
        recountCreation(
                folder,
                getOrderNumberEntities(folder)
        );
    }

    @Override
    public void recount(Folder folder, FolderUpdateDto dto) {
        recountUpdate(
                folder,
                dto,
                getOrderNumberEntities(folder)
        );
    }

    @Override
    public void recount(File file) {
        recountCreation(
                file,
                getOrderNumberEntities(file)
        );
    }

    @Override
    public void recount(File file, FileUpdateDto dto) {
        recountUpdate(
                file,
                dto,
                getOrderNumberEntities(file)
        );
    }

    private List<OrderNumberEntity> getOrderNumberEntities(File file) {
        List<OrderNumberEntity> orderNumberEntities;
        orderNumberEntities = new ArrayList<>(
                file.getFolder().getFiles()
        );
        orderNumberEntities.addAll(file.getFolder().getChildren());
        return orderNumberEntities;
    }

    private List<OrderNumberEntity> getOrderNumberEntities(Folder folder) {
        List<OrderNumberEntity> orderNumberEntities;
        if (folder.getParent() == null) {
            orderNumberEntities = new ArrayList<>(folder.getApplication().getRootFolders());
        } else {
            orderNumberEntities = new ArrayList<>(folder.getParent().getChildren());
            orderNumberEntities.addAll(folder.getParent().getFiles());
        }
        return orderNumberEntities;
    }

    public void recountCreation(
            OrderNumberEntity orderNumberEntity,
            List<OrderNumberEntity> orderNumberEntities
    ) {
        setMaxIndexIfNeed(
                true,
                orderNumberEntity,
                null,
                orderNumberEntity.getOrderNumber(),
                orderNumberEntities
        );
        setCorrectOrder(
                true,
                orderNumberEntity,
                orderNumberEntities,
                orderNumberEntity.getOrderNumber()
        );
    }

    private void recountUpdate(
            OrderNumberEntity orderNumberEntity,
            OrderNumberDto dto,
            List<OrderNumberEntity> orderNumberEntities
    ) {
        setMaxIndexIfNeed(
                false,
                orderNumberEntity,
                dto,
                dto.getOrderNumber(),
                orderNumberEntities
        );
        setCorrectOrder(
                false,
                orderNumberEntity,
                orderNumberEntities,
                dto.getOrderNumber()
        );
    }

    private void setMaxIndexIfNeed(
            boolean forCreation,
            OrderNumberEntity orderNumberEntity,
            OrderNumberDto dto,
            int orderNumber,
            List<OrderNumberEntity> orderNumberEntities
    ) {
        int maxOrderNumber = orderNumberEntities.size() - 1;
        if (forCreation) {
            maxOrderNumber++;
        }
        if (orderNumber > orderNumberEntities.size()) {
            orderNumberEntity.setOrderNumber(maxOrderNumber);
            if (dto != null) {
                dto.setOrderNumber(maxOrderNumber);
            }
        }
    }

    private void setCorrectOrder(
            boolean forCreation,
            OrderNumberEntity orderNumberEntity,
            List<OrderNumberEntity> orderNumberEntities,
            int orderNumber
    ) {
        sortByOrder(orderNumberEntities);
        if (!forCreation) {
            orderNumberEntities.remove(orderNumberEntity);
        }
        orderNumberEntities.add(orderNumber, orderNumberEntity);
        setCorrectOrder(orderNumberEntities);
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
