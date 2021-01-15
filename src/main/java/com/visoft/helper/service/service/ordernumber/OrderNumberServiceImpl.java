package com.visoft.helper.service.service.ordernumber;

import com.visoft.helper.service.persistance.entity.Folder;
import com.visoft.helper.service.persistance.entity.OrderNumber;
import com.visoft.helper.service.persistance.entity.file.File;
import com.visoft.helper.service.transport.dto.OrderNumberDto;
import com.visoft.helper.service.transport.dto.file.FileUpdateDto;
import com.visoft.helper.service.transport.dto.folder.FolderUpdateDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<OrderNumber> getSortedByOrderNumberChildrenCommonOrderNumbers(Folder parentFolder) {
        List<OrderNumber> orderNumbers = parentFolder.getChildren()
                .stream()
                .map(folder -> (OrderNumber) folder)
                .collect(Collectors.toList());

        orderNumbers.addAll(
                parentFolder.getFiles().stream()
                        .map(file -> (OrderNumber) file)
                        .collect(Collectors.toList())
        );
        sortByOrder(orderNumbers);
        return orderNumbers;
    }

    private void sortByOrder(List<? extends OrderNumber> collection) {
        collection.sort(Comparator.comparingInt(OrderNumber::getOrderNumber));
    }

    private List<OrderNumber> getOrderNumberEntities(File file) {
        List<OrderNumber> orderNumberEntities;
        orderNumberEntities = new ArrayList<>(
                file.getFolder().getFiles()
        );
        orderNumberEntities.addAll(file.getFolder().getChildren());
        return orderNumberEntities;
    }

    private List<OrderNumber> getOrderNumberEntities(Folder folder) {
        List<OrderNumber> orderNumberEntities;
        if (folder.getParent() == null) {
            orderNumberEntities = new ArrayList<>(folder.getApplication().getRootFolders());
        } else {
            orderNumberEntities = new ArrayList<>(folder.getParent().getChildren());
            orderNumberEntities.addAll(folder.getParent().getFiles());
        }
        return orderNumberEntities;
    }

    public void recountCreation(
            OrderNumber orderNumber,
            List<OrderNumber> orderNumberEntities
    ) {
        setMaxIndexIfNeed(
                true,
                orderNumber,
                null,
                orderNumber.getOrderNumber(),
                orderNumberEntities
        );
        setCorrectOrder(
                true,
                orderNumber,
                orderNumberEntities,
                orderNumber.getOrderNumber()
        );
    }

    private void recountUpdate(
            OrderNumber orderNumber,
            OrderNumberDto dto,
            List<OrderNumber> orderNumberEntities
    ) {
        setMaxIndexIfNeed(
                false,
                orderNumber,
                dto,
                dto.getOrderNumber(),
                orderNumberEntities
        );
        setCorrectOrder(
                false,
                orderNumber,
                orderNumberEntities,
                dto.getOrderNumber()
        );
    }

    private void setMaxIndexIfNeed(
            boolean forCreation,
            OrderNumber orderNumberEntity,
            OrderNumberDto dto,
            int orderNumber,
            List<OrderNumber> orderNumberEntities
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
            OrderNumber orderNumberEntity,
            List<OrderNumber> orderNumberEntities,
            int orderNumber
    ) {
        sortByOrder(orderNumberEntities);
        if (!forCreation) {
            orderNumberEntities.remove(orderNumberEntity);
        }
        orderNumberEntities.add(orderNumber, orderNumberEntity);
        setCorrectOrder(orderNumberEntities);
    }

    private void setCorrectOrder(List<? extends OrderNumber> collect) {
        for (int i = 0; i < collect.size(); i++) {
            collect.get(i).setOrderNumber(i);
        }
    }
}
