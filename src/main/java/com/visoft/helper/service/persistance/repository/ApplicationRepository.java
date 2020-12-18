package com.visoft.helper.service.persistance.repository;

import com.visoft.helper.service.persistance.entity.Application;

public interface ApplicationRepository extends GeneralRepository<Application> {

    boolean existsByNameEnOrNameHeOrNameRu(String nameEn, String nameHe, String nameRu);
}
