package com.visoft.helper.service.transport.mapper;

import com.visoft.helper.service.persistance.entity.Application;
import com.visoft.helper.service.transport.dto.application.ApplicationCreateDto;
import com.visoft.helper.service.transport.dto.application.ApplicationOutcomeDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ApplicationMapper {

    @Mapping(target = "name", source = "name")
    Application toEntity(ApplicationCreateDto dto);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    ApplicationOutcomeDto toDto(Application application);
}
