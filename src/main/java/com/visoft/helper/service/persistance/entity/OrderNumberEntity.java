package com.visoft.helper.service.persistance.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@Setter
public class OrderNumberEntity extends IdEntity {

    @Column(nullable = false)
    private int orderNumber;
}
