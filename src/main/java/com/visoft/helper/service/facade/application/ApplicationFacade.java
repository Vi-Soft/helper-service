package com.visoft.helper.service.facade.application;

import com.visoft.helper.service.persistance.entity.Application;
import com.visoft.helper.service.transport.dto.application.ApplicationCreateDto;
import com.visoft.helper.service.transport.dto.application.ApplicationOutcomeDto;
import com.visoft.helper.service.transport.dto.application.ApplicationUpdateDto;

import java.util.List;

public interface ApplicationFacade {

    ApplicationOutcomeDto create(ApplicationCreateDto dto);

    List<ApplicationOutcomeDto> getAll();

    Application getByIdUnsafe(Long id);

    ApplicationOutcomeDto update(Long id, ApplicationUpdateDto dto);
}
