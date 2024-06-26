package com.visoft.helper.service.service.application;

import com.visoft.helper.service.exception.application.ApplicationNotFoundException;
import com.visoft.helper.service.persistance.entity.Application;
import com.visoft.helper.service.persistance.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;

    @Override
    public Application save(Application application) {
        return applicationRepository.save(application);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByNameEnOrNameRuOrNameHe(
            String nameEn,
            String nameRu,
            String nameHe
    ) {
        return applicationRepository.existsByNameEnOrNameRuOrNameHe(nameEn, nameRu, nameHe);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByIdNotAndNameEnOrNameRuOrNameHe(
            Long id,
            String nameEn,
            String nameRu,
            String nameHe
    ) {
        return applicationRepository.existsByIdNotAndNameEnOrIdNotAndNameRuOrIdNotAndNameHe(
                id,
                nameEn,
                id,
                nameRu,
                id,
                nameHe
        );
    }

    @Override
    @Transactional(readOnly = true)
    public List<Application> findAll() {
        return applicationRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Application findByIdUnsafe(Long id) {
        return applicationRepository.findById(id).orElseThrow(ApplicationNotFoundException::new);
    }

    @Override
    public void delete(Application application) {
        applicationRepository.delete(application);
    }
}
