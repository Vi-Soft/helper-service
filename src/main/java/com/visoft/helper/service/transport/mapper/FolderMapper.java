package com.visoft.helper.service.transport.mapper;

import com.visoft.helper.service.facade.application.ApplicationFacade;
import com.visoft.helper.service.persistance.entity.Folder;
import com.visoft.helper.service.transport.dto.folder.FolderOutcomeDto;
import com.visoft.helper.service.transport.dto.folder.FolderRootCreateDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper
public abstract class FolderMapper {

    @Autowired
    ApplicationFacade applicationFacade;

    @Autowired
    GeneralMapper generalMapper;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "name")
    @Mapping(target = "folderOrder", constant = "0L")
    @Mapping(target = "root", constant = "true")
    @Mapping(target = "parent", expression = "java(null)")
    @Mapping(target = "application", expression = "java(applicationFacade.getByIdUnsafe(dto.getApplicationId()))")
    @Mapping(target = "children", ignore = true)
    @Mapping(target = "files", ignore = true)
    public abstract Folder toEntity(FolderRootCreateDto dto);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "fileOrder", source = "folderOrder")
    @Mapping(target = "root", source = "root")
    @Mapping(target = "parentId", expression = "java(folder.getParent()==null?null:folder.getParent().getId())")
    @Mapping(target = "applicationId", source = "application.id")
    @Mapping(target = "childrenIds", expression = "java(generalMapper.toIds(folder.getChildren()))")
    @Mapping(target = "fileIds", expression = "java(generalMapper.toIds(folder.getFiles()))")
    public abstract FolderOutcomeDto toDto(Folder folder);


}
