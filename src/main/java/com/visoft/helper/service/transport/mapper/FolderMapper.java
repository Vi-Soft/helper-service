package com.visoft.helper.service.transport.mapper;

import com.visoft.helper.service.facade.application.ApplicationFacade;
import com.visoft.helper.service.persistance.entity.Folder;
import com.visoft.helper.service.persistance.entity.IdEntity;
import com.visoft.helper.service.transport.dto.folder.FolderOutcomeDto;
import com.visoft.helper.service.transport.dto.folder.FolderRootCreateDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(uses = ApplicationMapper.class)
public abstract class FolderMapper {

    @Autowired
    ApplicationFacade applicationFacade;

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
    @Mapping(target = "childrenIds", expression = "java(toIds(folder.getChildren()))")
    @Mapping(target = "fileIds", expression = "java(toIds(folder.getFiles()))")
    public abstract FolderOutcomeDto toDto(Folder folder);

    <T extends IdEntity> Set<Long> toIds(Set<T> children) {
        if (children != null && !children.isEmpty()) {
            return children.stream().map(IdEntity::getId).collect(Collectors.toSet());
        }
        return Collections.emptySet();
    }
}
