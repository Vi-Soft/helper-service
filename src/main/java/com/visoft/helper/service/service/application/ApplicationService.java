package com.visoft.helper.service.service.application;

import com.visoft.helper.service.persistance.entity.Application;

import java.util.List;

public interface ApplicationService {

    Application save(Application application);

    boolean existsByNameEnOrNameRuOrNameHe(
            String nameEn,
            String nameRu,
            String nameHe
    );

    boolean existsByIdNotAndNameEnOrNameRuOrNameHe(
            Long id,
            String nameEn,
            String nameRu,
            String nameHe
    );

    List<Application> findAll();

    Application findByIdUnsafe(Long id);

    void delete(Application application);
}
