package com.visoft.helper.service.persistance.repository;

import com.visoft.helper.service.persistance.entity.Application;

public interface ApplicationRepository extends GeneralRepository<Application> {

    boolean existsByNameEnOrNameRuOrNameHe(
            String nameEn,
            String nameRu,
            String nameHe
    );

    boolean existsByIdNotAndNameEnOrIdNotAndNameRuOrIdNotAndNameHe(
            Long firstId,
            String nameEn,
            Long secondId,
            String nameRu,
            Long thirdId,
            String nameHe
    );
}
