package com.visoft.helper.service.transport.mapper.application;

import com.visoft.helper.service.persistance.entity.Application;
import com.visoft.helper.service.persistance.entity.File;
import com.visoft.helper.service.persistance.entity.Folder;
import com.visoft.helper.service.transport.dto.application.ApplicationCreateDto;
import com.visoft.helper.service.transport.dto.application.ApplicationOutcomeDto;
import com.visoft.helper.service.transport.dto.application.ApplicationUpdateDto;
import com.visoft.helper.service.transport.dto.tree.ApplicationTreeOutcomeDto;
import com.visoft.helper.service.transport.dto.tree.TreeContentDto;
import com.visoft.helper.service.transport.mapper.GeneralMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper
public abstract class ApplicationMapper {

    @Autowired
    GeneralMapper generalMapper;

    @Mapping(target = "nameEn", source = "nameEn")
    @Mapping(target = "nameHe", source = "nameHe")
    @Mapping(target = "nameRu", source = "nameRu")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "folders", ignore = true)
    public abstract Application toEntity(ApplicationCreateDto dto);

    @Mapping(target = "nameEn", source = "nameEn")
    @Mapping(target = "nameHe", source = "nameHe")
    @Mapping(target = "nameRu", source = "nameRu")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "folders", ignore = true)
    public abstract void toEntity(ApplicationUpdateDto dto, @MappingTarget Application application);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "nameEn", source = "nameEn")
    @Mapping(target = "nameHe", source = "nameHe")
    @Mapping(target = "nameRu", source = "nameRu")
    @Mapping(target = "folderIds", expression = "java(generalMapper.toIds(application.getFolders()))")
    public abstract ApplicationOutcomeDto toDto(Application application);

    @Mapping(target = "nameEn", source = "nameEn")
    @Mapping(target = "nameHe", source = "nameHe")
    @Mapping(target = "nameRu", source = "nameRu")
    @Mapping(target = "content", expression = "java(new java.util.ArrayList<>())")
    public abstract ApplicationTreeOutcomeDto toTreeDto(Application application);

    @Mapping(target = "path", ignore = true)
    @Mapping(target = "orderNumber", source = "orderNumber")
    @Mapping(target = "nameEn", source = "nameEn")
    @Mapping(target = "nameHe", source = "nameHe")
    @Mapping(target = "nameRu", source = "nameRu")
    @Mapping(target = "content", expression = "java(new java.util.ArrayList<>())")
    public abstract TreeContentDto toTreeDto(Folder folder);

    @Mapping(target = "path", source = "path")
    @Mapping(target = "content", ignore = true)
    @Mapping(target = "orderNumber", source = "orderNumber")
    @Mapping(target = "nameEn", source = "nameEn")
    @Mapping(target = "nameHe", source = "nameHe")
    @Mapping(target = "nameRu", source = "nameRu")
    public abstract TreeContentDto toTreeDto(File folder);

    public abstract List<ApplicationOutcomeDto> toDto(List<Application> applications);
}
