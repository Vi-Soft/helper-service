package com.visoft.helper.service.persistance.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@Setter
public class OrderNumber extends MultiLanguageName {

    @Column(nullable = false)
    private int orderNumber;
}
