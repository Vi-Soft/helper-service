package com.visoft.helper.service.persistance.entity.file;

import com.visoft.helper.service.persistance.entity.OrderNumber;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@Setter
public class PathMultiLanguage extends OrderNumber {

    @Column(nullable = false)
    private String pathEn;

    @Column(nullable = false)
    private String pathRu;

    @Column(nullable = false)
    private String pathHe;
}
    