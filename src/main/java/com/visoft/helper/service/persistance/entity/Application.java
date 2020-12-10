package com.visoft.helper.service.persistance.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
public class Application extends IdEntity {

    @Column(nullable = false, unique = true)
    private String name;

    @OneToOne(mappedBy = "parent")
    private Folder rootFolder;
}
