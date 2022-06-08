package com.visoft.helper.service.transport.mapper;

import com.visoft.helper.service.persistance.entity.IdEntity;
import com.visoft.helper.service.persistance.entity.file.File;
import com.visoft.helper.service.service.application.ApplicationService;
import com.visoft.helper.service.service.folder.FolderService;
import com.visoft.helper.service.transport.dto.file.FileCreateDto;
import com.visoft.helper.service.transport.dto.file.FileOutcomeDto;
import com.visoft.helper.service.transport.dto.file.FileUpdateDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Mapper(
        imports = {
                Optional.class,
                IdEntity.class
        }
)
public abstract class FileMapper {

    @Autowired
    FolderService folderService;

    @Autowired
    ApplicationService applicationService;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "nameEn", source = "nameEn")
    @Mapping(target = "nameHe", source = "nameHe")
    @Mapping(target = "nameRu", source = "nameRu")
    @Mapping(target = "pathEn", source = "pathEn")
    @Mapping(target = "pathRu", source = "pathRu")
    @Mapping(target = "pathHe", source = "pathHe")
    @Mapping(target = "orderNumber", source = "orderNumber")
    @Mapping(target = "folder", expression = "java(Optional.ofNullable(dto.getFolderId()).map(folderService::findByIdUnsafe).orElse(null) )")
    @Mapping(target = "application", expression = "java(applicationService.findByIdUnsafe(dto.getApplicationId()))")
    public abstract File toEntity(FileCreateDto dto);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "nameEn", source = "nameEn")
    @Mapping(target = "nameHe", source = "nameHe")
    @Mapping(target = "nameRu", source = "nameRu")
    @Mapping(target = "pathEn", source = "pathEn")
    @Mapping(target = "pathRu", source = "pathRu")
    @Mapping(target = "pathHe", source = "pathHe")
    @Mapping(target = "orderNumber", source = "orderNumber")
    @Mapping(target = "folderId", expression = "java(Optional.ofNullable(file.getFolder()).map(IdEntity::getId).orElse(null))")
    @Mapping(target = "applicationId", source = "application.id")
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
    @Mapping(target = "application", ignore = true)
    public abstract void toEntity(FileUpdateDto dto, @MappingTarget File file);

    @Mapping(target = "nameEn", source = "file.nameEn")
    @Mapping(target = "nameHe", source = "file.nameHe")
    @Mapping(target = "nameRu", source = "file.nameRu")
    @Mapping(target = "pathEn", source = "file.pathEn")
    @Mapping(target = "pathRu", source = "file.pathRu")
    @Mapping(target = "pathHe", source = "file.pathHe")
    @Mapping(target = "orderNumber", source = "file.orderNumber")
    @Mapping(target = "folderId", source = "folderId")
    @Mapping(target = "applicationId", source = "file.application.id")
    public abstract FileCreateDto toCreateDto(File file, Long folderId);
}
