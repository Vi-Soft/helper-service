package com.visoft.helper.service.facade.application;

import com.visoft.helper.service.transport.dto.application.ApplicationCreateDto;
import com.visoft.helper.service.transport.dto.application.ApplicationOutcomeDto;

public interface ApplicationFacade {

    ApplicationOutcomeDto create(ApplicationCreateDto dto);
}
