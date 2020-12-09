package com.visoft.helper.service.service.application;

import com.visoft.helper.service.persistance.entity.Application;

public interface ApplicationService {

    Application save(Application application);

    boolean existsByName(String name);
}
