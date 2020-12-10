package com.visoft.helper.service.transport.mapper;

import com.visoft.helper.service.persistance.entity.Folder;
import com.visoft.helper.service.service.application.ApplicationService;
import com.visoft.helper.service.service.folder.FolderService;
import com.visoft.helper.service.transport.dto.folder.FolderCreateDto;
import com.visoft.helper.service.transport.dto.folder.FolderOutcomeDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper
public abstract class FolderMapper {

    @Autowired
    ApplicationService applicationService;

    @Autowired
    FolderService folderService;

    @Autowired
    GeneralMapper generalMapper;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "name")
    @Mapping(target = "folderOrder", source = "folderOrder")
    @Mapping(
            target = "parent",
            expression = "java(dto.getParentId()==null?null:folderService.findByIdUnsafe(dto.getParentId()))"
    )
    @Mapping(target = "application", expression = "java(applicationService.findByIdUnsafe(dto.getApplicationId()))"
    )
    @Mapping(target = "children", ignore = true)
    @Mapping(target = "files", ignore = true)
    public abstract Folder toEntity(FolderCreateDto dto);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "fileOrder", source = "folderOrder")
    @Mapping(target = "parentId", expression = "java(folder.getParent()==null?null:folder.getParent().getId())")
    @Mapping(target = "applicationId", source = "application.id")
    @Mapping(target = "childrenIds", expression = "java(generalMapper.toIds(folder.getChildren()))")
    @Mapping(target = "fileIds", expression = "java(generalMapper.toIds(folder.getFiles()))")
    public abstract FolderOutcomeDto toDto(Folder folder);


}
