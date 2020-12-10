package com.visoft.helper.service.persistance.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@Getter
@Setter
public class Application extends IdEntity {

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "parent")
    private Set<Folder> folders;
}
