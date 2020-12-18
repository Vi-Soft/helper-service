package com.visoft.helper.service.facade.application;

import com.visoft.helper.service.exception.application.ApplicationAlreadyExistsException;
import com.visoft.helper.service.persistance.entity.Application;
import com.visoft.helper.service.service.application.ApplicationService;
import com.visoft.helper.service.transport.dto.application.ApplicationCreateDto;
import com.visoft.helper.service.transport.dto.application.ApplicationOutcomeDto;
import com.visoft.helper.service.transport.dto.application.ApplicationUpdateDto;
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

    @Override
    public ApplicationOutcomeDto update(Long id, ApplicationUpdateDto dto) {
        Application application = applicationService.findByIdUnsafe(id);
        validateUpdate(dto);
        applicationMapper.toEntity(dto, application);
        return applicationMapper.toDto(
                applicationService.save(application)
        );
    }

    @Override
    public void delete(Long id) {
        applicationService.delete(
                applicationService.findByIdUnsafe(id)
        );
    }

    @Override
    public ApplicationOutcomeDto getById(Long id) {
        return applicationMapper.toDto(
                applicationService.findByIdUnsafe(id)
        );
    }

    private void validateUpdate(ApplicationUpdateDto dto) {
        existsByNameUnsafe(dto.getNameEn(), dto.getNameHe(), dto.getNameRu());
    }

    private void validateCreation(Application application) {
        existsByNameUnsafe(application.getNameEn(), application.getNameHe(), application.getNameRu());
    }

    private void existsByNameUnsafe(String nameEn,
                                    String nameHe,
                                    String nameRu) {
        if (applicationService.existsByName(nameEn, nameHe, nameRu)) {
            throw new ApplicationAlreadyExistsException();
        }
    }
}
