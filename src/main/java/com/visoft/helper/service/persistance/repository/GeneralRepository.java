package com.visoft.helper.service.persistance.repository;

import com.visoft.helper.service.persistance.entity.IdEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeneralRepository<T extends IdEntity> extends JpaRepository<T, Long> {
}
