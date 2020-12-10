package com.visoft.helper.service.facade.application;

import com.visoft.helper.service.exception.application.ApplicationAlreadyExistsException;
import com.visoft.helper.service.persistance.entity.Application;
import com.visoft.helper.service.service.application.ApplicationService;
import com.visoft.helper.service.transport.dto.application.ApplicationCreateDto;
import com.visoft.helper.service.transport.dto.application.ApplicationOutcomeDto;
import com.visoft.helper.service.transport.mapper.ApplicationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ApplicationFacadeImpl implements ApplicationFacade {

    private final ApplicationService applicationService;
    private final ApplicationMapper applicationMapper;

    @Override
    public ApplicationOutcomeDto create(ApplicationCreateDto dto) {
        Application application = applicationMapper.toEntity(dto);
        validateCreation(application);
        return applicationMapper.toDto(
                applicationService.save(application)
        );
    }

    @Override
    public List<ApplicationOutcomeDto> getAll() {
        return applicationMapper.toDto(applicationService.findAll());
    }

    @Override
    public Application getByIdUnsafe(Long id) {
        return applicationService.findByIdUnsafe(id);
    }

    private void validateCreation(Application application) {
        existsByNameUnsafe(application.getName());
    }

    private void existsByNameUnsafe(String name) {
        if (applicationService.existsByName(name)) {
            throw new ApplicationAlreadyExistsException();
        }
    }
}
