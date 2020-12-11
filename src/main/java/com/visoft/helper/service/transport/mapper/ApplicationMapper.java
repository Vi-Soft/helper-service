package com.visoft.helper.service.transport.mapper;

import com.visoft.helper.service.persistance.entity.Application;
import com.visoft.helper.service.transport.dto.application.ApplicationCreateDto;
import com.visoft.helper.service.transport.dto.application.ApplicationOutcomeDto;
import com.visoft.helper.service.transport.dto.application.ApplicationUpdateDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper
public abstract class ApplicationMapper {

    @Autowired
    GeneralMapper generalMapper;

    @Mapping(target = "name", source = "name")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "folders", ignore = true)
    public abstract Application toEntity(ApplicationCreateDto dto);

    @Mapping(target = "name", source = "name")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "folders", ignore = true)
    public abstract void toEntity(ApplicationUpdateDto dto, @MappingTarget Application application);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "folderIds", expression = "java(generalMapper.toIds(application.getFolders()))")
    public abstract ApplicationOutcomeDto toDto(Application application);

    public abstract List<ApplicationOutcomeDto> toDto(List<Application> applications);
}