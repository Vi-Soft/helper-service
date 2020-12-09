package com.visoft.helper.service.service.application;

import com.visoft.helper.service.persistance.entity.Application;
import com.visoft.helper.service.persistance.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public boolean existsByName(String name) {
        return applicationRepository.existsByName(name);
    }
}
