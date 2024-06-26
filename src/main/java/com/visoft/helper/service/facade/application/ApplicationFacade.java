package com.visoft.helper.service.facade.application;

import com.visoft.helper.service.transport.dto.application.ApplicationCopyDto;
import com.visoft.helper.service.transport.dto.application.ApplicationCreateDto;
import com.visoft.helper.service.transport.dto.application.ApplicationOutcomeDto;
import com.visoft.helper.service.transport.dto.application.ApplicationUpdateDto;

import java.util.List;

public interface ApplicationFacade {

    ApplicationOutcomeDto create(ApplicationCreateDto dto);

    List<ApplicationOutcomeDto> getAll();

    ApplicationOutcomeDto update(Long id, ApplicationUpdateDto dto);

    void delete(Long id);

    ApplicationOutcomeDto getById(Long id);

    void copy(Long id, ApplicationCopyDto dto);
}
