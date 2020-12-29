package com.visoft.helper.service.service.ordernumber;

import com.visoft.helper.service.persistance.entity.File;
import com.visoft.helper.service.persistance.entity.Folder;
import com.visoft.helper.service.persistance.entity.OrderNumberEntity;
import com.visoft.helper.service.service.file.FileService;
import com.visoft.helper.service.service.folder.FolderService;
import com.visoft.helper.service.transport.dto.folder.FolderUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderNumberServiceImpl implements OrderNumberService {

    private final FolderService folderService;
    private final FileService fileService;

    @Override
    public void recountCreateFolder(Folder folder) {
        List<OrderNumberEntity> orderNumberEntities;
        if (folder.getParent() == null) {
            orderNumberEntities = new ArrayList<>(folder.getApplication().getRootFolders());
        } else {
            orderNumberEntities = new ArrayList<>(
                    folderService.findAllByParent(folder.getParent())
            );
            orderNumberEntities.addAll(folder.getParent().getFiles());

        }
        if (folder.getOrderNumber() > orderNumberEntities.size()) {
            folder.setOrderNumber(orderNumberEntities.size());
        }
        sortByOrder(orderNumberEntities);
        orderNumberEntities.add(folder.getOrderNumber(), folder);
        setCorrectOrder(orderNumberEntities);
    }

    @Override
    public void recountCreateFile(File file) {
        List<OrderNumberEntity> orderNumberEntities;
        Folder folder = file.getFolder();
        orderNumberEntities = new ArrayList<>(
                fileService.findAllByFolder(file.getFolder())
        );
        orderNumberEntities.addAll(folder.getChildren());

        if (file.getOrderNumber() > orderNumberEntities.size()) {
            file.setOrderNumber(orderNumberEntities.size());
        }
        sortByOrder(orderNumberEntities);
        orderNumberEntities.add(file.getOrderNumber(), folder);
        setCorrectOrder(orderNumberEntities);
    }

    @Override
    public void recountUpdateFolder(Folder folder, FolderUpdateDto dto) {
        List<OrderNumberEntity> orderNumberEntities;
        if (folder.getParent() == null) {
            orderNumberEntities = new ArrayList<>(folder.getApplication().getRootFolders());
        } else {
            orderNumberEntities = new ArrayList<>(
                    folderService.findAllByParent(folder.getParent())
            );
            orderNumberEntities.addAll(folder.getParent().getFiles());

        }
        if (dto.getOrderNumber() > orderNumberEntities.size()) {
            dto.setOrderNumber(orderNumberEntities.size());
            folder.setOrderNumber(dto.getOrderNumber());
        }
        sortByOrder(orderNumberEntities);
        orderNumberEntities.remove(folder);
        orderNumberEntities.add(dto.getOrderNumber(), folder);
        setCorrectOrder(orderNumberEntities);
    }


//    @Override
//    public void recountCreateFolder(Folder folder) {
//        if (folder.getParent() == null) {
//            List<Folder> rootFolders = folder.getApplication().getRootFolders();
//            sortByOrder(rootFolders);
//            if (rootFolders.isEmpty()) {
//                folder.setOrderNumber(0);
//            } else {
//                int size = rootFolders.size();
//                if (folder.getOrderNumber() > size) {
//                    folder.setOrderNumber(size);
//                } else {
//                    for (int i = folder.getOrderNumber(); i < size; i++) {
//                        rootFolders.get(i).setOrderNumber(i + 1);
//                    }
//                }
//            }
//        } else {
//            List<Folder> foldersInSameLevel = folderService.findAllByParent(folder.getParent());
//            sortByOrder(foldersInSameLevel);
//            List<File> filesInSameLevel = folder.getParent().getFiles();
//            sortByOrder(filesInSameLevel);
//            int folderSize = foldersInSameLevel.size();
//            int fileSize = filesInSameLevel.size();
//
//            if (folderSize == 0) {
//                if (fileSize == 0) {
//                    folder.setOrderNumber(0);
//                } else {
//                    if (folder.getOrderNumber() > fileSize) {
//                        folder.setOrderNumber(fileSize);
//                    } else {
//                        for (int i = fileSize; i < filesInSameLevel.size(); i++) {
//                            filesInSameLevel.get(i).setOrderNumber(i + 1);
//                        }
//                    }
//                }
//            } else {
//                if (fileSize == 0) {
//                    if (folder.getOrderNumber() > folderSize) {
//                        folder.setOrderNumber(folderSize);
//                    } else {
//                        for (int i = folder.getOrderNumber(); i < foldersInSameLevel.size(); i++) {
//                            foldersInSameLevel.get(i).setOrderNumber(i + 1);
//                        }
//                    }
//                } else {
//                    if (folder.getOrderNumber() > folderSize + fileSize) {
//                        folder.setOrderNumber(folderSize + fileSize);
//                    } else {
//                        for (int i = folder.getOrderNumber(); i < foldersInSameLevel.size(); i++) {
//                            foldersInSameLevel.get(i).setOrderNumber(i + 1);
//                        }
//                        for (int i = folder.getOrderNumber(); i < filesInSameLevel.size(); i++) {
//                            filesInSameLevel.get(i).setOrderNumber(i + 1);
//                        }
//                    }
//                }
//            }
//        }
//    }

//    @Override
//    public void recountUpdateFolder(Folder folder, FolderUpdateDto dto) {
//        int currentOrder = dto.getOrderNumber();
//        int previousOrder = folder.getOrderNumber();
//        if (folder.getParent() == null) {
//            List<Folder> rootFolders = folder.getApplication().getRootFolders();
//            sortByOrder(rootFolders);
//            if (rootFolders.size() == 1) {
//                dto.setOrderNumber(folder.getOrderNumber());
//            } else {
//                int size = rootFolders.size();
//
//                if (currentOrder > size) {
//                    dto.setOrderNumber(size);
//                    return;
//                }
//                if (currentOrder < previousOrder) {
//                    for (int i = currentOrder; i < previousOrder; i++) {
//                        rootFolders.get(i).setOrderNumber(i + 1);
//                    }
//                }
//                if (currentOrder > previousOrder) {
//                    for (int i = previousOrder + 1; i < currentOrder; i++) {
//                        rootFolders.get(i).setOrderNumber(i - 1);
//                    }
//                }
//            }
//        } else {
//            List<Folder> foldersInSameLevel = folderService.findAllByParent(folder.getParent());
//            sortByOrder(foldersInSameLevel);
//            List<File> filesInSameLevel = folder.getParent().getFiles();
//            sortByOrder(filesInSameLevel);
//            int folderSize = foldersInSameLevel.size();
//            int fileSize = filesInSameLevel.size();
//
////            if (folderSize == 0) {
////                if (fileSize == 0) {
////                    folder.setOrderNumber(0);
////                } else {
////                    if (folder.getOrderNumber() > fileSize) {
////                        folder.setOrderNumber(fileSize);
////                    } else {
////                        for (int i = fileSize; i < filesInSameLevel.size(); i++) {
////                            filesInSameLevel.get(i).setOrderNumber(i+1);
////                        }
////                    }
////                }
////            } else {
//            if (fileSize == 0) {
//                if (folder.getOrderNumber() > folderSize) {
//                    dto.setOrderNumber(folderSize);
//                    return;
//                }
//                if (currentOrder < previousOrder) {
//                    for (int i = currentOrder; i < previousOrder; i++) {
//                        foldersInSameLevel.get(i).setOrderNumber(i + 1);
//                    }
//                }
//                if (currentOrder > previousOrder) {
//                    for (int i = previousOrder + 1; i < currentOrder; i++) {
//                        foldersInSameLevel.get(i).setOrderNumber(i - 1);
//                    }
//                }
//            } else {
//                if (folder.getOrderNumber() > folderSize + fileSize) {
//                    dto.setOrderNumber(folderSize + fileSize);
//                    return;
//                }
//                if (currentOrder < previousOrder) {
//                    List<Folder> plusOrderFolderIds = new ArrayList<>();
//                    List<File> plusOrderFileIds = new ArrayList<>();
//                    for (int i = currentOrder; i < previousOrder; i++) {
//                        for (Folder folder1 : foldersInSameLevel) {
//                            if (folder1.getOrderNumber() == i) {
//                                plusOrderFolderIds.add(folder1);
//                            }
//                        }
//                        for (File file1 : filesInSameLevel) {
//                            if (file1.getOrderNumber() == i) {
//                                plusOrderFileIds.add(file1);
//                            }
//                        }
//                    }
//                    for (Folder folder1 : foldersInSameLevel) {
//                        for (Folder plusOrderFolderId : plusOrderFolderIds) {
//                            if (folder1.equals(plusOrderFolderId)) {
//                                folder1.setOrderNumber(folder1.getOrderNumber() + 1);
//                            }
//                        }
//                    }
//                    for (File file1 : filesInSameLevel) {
//                        for (File plusOrderFileId : plusOrderFileIds) {
//                            if (file1.equals(plusOrderFileId)) {
//                                file1.setOrderNumber(file1.getOrderNumber() + 1);
//                            }
//                        }
//                    }
//                }
//                if (currentOrder > previousOrder) {
//                    List<Folder> minusOrderFolderIds = new ArrayList<>();
//                    List<File> minusOrderFileIds = new ArrayList<>();
//                    for (int i = previousOrder + 1; i <= currentOrder; i++) {
//                        for (Folder folder1 : foldersInSameLevel) {
//                            if (folder1.getOrderNumber() == i) {
//                                minusOrderFolderIds.add(folder1);
//                            }
//                        }
//                        for (File file1 : filesInSameLevel) {
//                            if (file1.getOrderNumber() == i) {
//                                minusOrderFileIds.add(file1);
//                            }
//                        }
//                    }
//                    for (Folder folder1 : foldersInSameLevel) {
//                        for (Folder plusOrderFolderId : minusOrderFolderIds) {
//                            if (folder1.equals(plusOrderFolderId)) {
//                                folder1.setOrderNumber(folder1.getOrderNumber() - 1);
//                            }
//                        }
//                    }
//                    for (File file1 : filesInSameLevel) {
//                        for (File plusOrderFileId : minusOrderFileIds) {
//                            if (file1.equals(plusOrderFileId)) {
//                                file1.setOrderNumber(file1.getOrderNumber() - 1);
//                            }
//                        }
//                    }
//                }
//            }
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

//    @Override
//    public int recountFolderOrder2(
//            List<Folder> folderInSameFolder,
//            Folder folder,
//            int order,
//            Integer previousOrder
//    ) {
//        sortByOrder(folderInSameFolder);
//        if (folder == null) {
//            folder = folderInSameFolder.get(previousOrder);
//        }
//        if (previousOrder != null) {
//            folderInSameFolder.remove(folder);
//        }
//        if (order > folderInSameFolder.size()) {
//            order = folderInSameFolder.size();
//        }
//        folderInSameFolder.add(order, folder);
//        setCorrectOrder(folderInSameFolder);
//        return order;
//    }

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
