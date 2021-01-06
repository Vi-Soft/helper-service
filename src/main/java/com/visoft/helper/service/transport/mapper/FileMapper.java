package com.visoft.helper.service.transport.mapper;

import com.visoft.helper.service.facade.folder.FolderFacade;
import com.visoft.helper.service.persistance.entity.file.File;
import com.visoft.helper.service.transport.dto.file.FileCreateDto;
import com.visoft.helper.service.transport.dto.file.FileOutcomeDto;
import com.visoft.helper.service.transport.dto.file.FileUpdateDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper
public abstract class FileMapper {

    @Autowired
    FolderFacade folderFacade;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "nameEn", source = "nameEn")
    @Mapping(target = "nameHe", source = "nameHe")
    @Mapping(target = "nameRu", source = "nameRu")
    @Mapping(target = "pathEn", source = "pathEn")
    @Mapping(target = "pathRu", source = "pathRu")
    @Mapping(target = "pathHe", source = "pathHe")
    @Mapping(target = "orderNumber", source = "orderNumber")
    @Mapping(target = "folder", expression = "java(folderFacade.getByIdUnsafe(dto.getFolderId()))")
    public abstract File toEntity(FileCreateDto dto);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "nameEn", source = "nameEn")
    @Mapping(target = "nameHe", source = "nameHe")
    @Mapping(target = "nameRu", source = "nameRu")
    @Mapping(target = "pathEn", source = "pathEn")
    @Mapping(target = "pathRu", source = "pathRu")
    @Mapping(target = "pathHe", source = "pathHe")
    @Mapping(target = "orderNumber", source = "orderNumber")
    @Mapping(target = "folderId", source = "folder.id")
    public abstract FileOutcomeDto toDto(File file);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "nameEn", source = "nameEn")
    @Mapping(target = "nameHe", source = "nameHe")
    @Mapping(target = "nameRu", source = "nameRu")
    @Mapping(target = "pathEn", source = "pathEn")
    @Mapping(target = "pathRu", source = "pathRu")
    @Mapping(target = "pathHe", source = "pathHe")
    @Mapping(target = "orderNumber", source = "orderNumber")
    @Mapping(target = "folder", ignore = true)
    public abstract void toEntity(FileUpdateDto dto, @MappingTarget File file);
}
