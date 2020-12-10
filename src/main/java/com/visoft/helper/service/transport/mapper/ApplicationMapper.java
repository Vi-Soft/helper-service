package com.visoft.helper.service.transport.mapper;

import com.visoft.helper.service.persistance.entity.Application;
import com.visoft.helper.service.transport.dto.application.ApplicationCreateDto;
import com.visoft.helper.service.transport.dto.application.ApplicationOutcomeDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface ApplicationMapper {

    @Mapping(target = "name", source = "name")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "rootFolder", ignore = true)
    Application toEntity(ApplicationCreateDto dto);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "rootFolderId", source = "rootFolder.id")
    ApplicationOutcomeDto toDto(Application application);

    List<ApplicationOutcomeDto> toDto(List<Application> applications);
}
