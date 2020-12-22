package com.visoft.helper.service.transport.mapper.application;

import com.visoft.helper.service.persistance.entity.Application;
import com.visoft.helper.service.persistance.entity.File;
import com.visoft.helper.service.persistance.entity.Folder;
import com.visoft.helper.service.transport.dto.tree.ApplicationTreeOutcomeDto;
import com.visoft.helper.service.transport.dto.tree.TreeContentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public abstract class ApplicationTreeMapper {

    @Mapping(target = "nameEn", source = "nameEn")
    @Mapping(target = "nameHe", source = "nameHe")
    @Mapping(target = "nameRu", source = "nameRu")
    @Mapping(target = "content", expression = "java(new java.util.ArrayList<>())")
    public abstract ApplicationTreeOutcomeDto toDto(Application application);

    @Mapping(target = "path", ignore = true)
    @Mapping(target = "orderNumber", source = "orderNumber")
    @Mapping(target = "nameEn", source = "nameEn")
    @Mapping(target = "nameHe", source = "nameHe")
    @Mapping(target = "nameRu", source = "nameRu")
    @Mapping(target = "content", expression = "java(new java.util.ArrayList<>())")
    public abstract TreeContentDto toDto(Folder folder);

    @Mapping(target = "path", source = "path")
    @Mapping(target = "content", ignore = true)
    @Mapping(target = "orderNumber", source = "orderNumber")
    @Mapping(target = "nameEn", source = "nameEn")
    @Mapping(target = "nameHe", source = "nameHe")
    @Mapping(target = "nameRu", source = "nameRu")
    public abstract TreeContentDto toDto(File folder);
}
