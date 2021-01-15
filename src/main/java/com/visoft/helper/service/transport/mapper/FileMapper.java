package com.visoft.helper.service.transport.mapper;

import com.visoft.helper.service.persistance.entity.file.File;
import com.visoft.helper.service.service.folder.FolderService;
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
    FolderService folderService;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "nameEn", source = "nameEn")
    @Mapping(target = "nameHe", source = "nameHe")
    @Mapping(target = "nameRu", source = "nameRu")
    @Mapping(target = "pathEn", source = "pathEn")
    @Mapping(target = "pathRu", source = "pathRu")
    @Mapping(target = "pathHe", source = "pathHe")
    @Mapping(target = "orderNumber", source = "orderNumber")
    @Mapping(target = "folder", expression = "java(folderService.findByIdUnsafe(dto.getFolderId()))")
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

    @Mapping(target = "nameEn", source = "file.nameEn")
    @Mapping(target = "nameHe", source = "file.nameHe")
    @Mapping(target = "nameRu", source = "file.nameRu")
    @Mapping(target = "pathEn", source = "file.pathEn")
    @Mapping(target = "pathRu", source = "file.pathRu")
    @Mapping(target = "pathHe", source = "file.pathHe")
    @Mapping(target = "orderNumber", source = "file.orderNumber")
    @Mapping(target = "folderId", source = "folderId")
    public abstract FileCreateDto toCreateDto(File file, Long folderId);
}
