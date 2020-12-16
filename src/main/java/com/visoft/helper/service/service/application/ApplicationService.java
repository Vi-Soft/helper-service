package com.visoft.helper.service.service.application;

import com.visoft.helper.service.persistance.entity.Application;

import java.util.List;

public interface ApplicationService {

    Application save(Application application);

    boolean existsByName(String name);

    List<Application> findAll();

    Application findByIdUnsafe(Long id);

    void delete(Application application);
}
