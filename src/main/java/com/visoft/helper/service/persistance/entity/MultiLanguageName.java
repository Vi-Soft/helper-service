package com.visoft.helper.service.persistance.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@Setter
public class MultiLanguageName extends IdEntity {

    @Column(nullable = false)
    private String nameEn;

    @Column(nullable = false)
    private String nameRu;

    @Column(nullable = false)
    private String nameHe;
}
