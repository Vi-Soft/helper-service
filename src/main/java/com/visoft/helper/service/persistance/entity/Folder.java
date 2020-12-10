package com.visoft.helper.service.persistance.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
public class Folder extends IdEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private long folderOrder;

    @Column(nullable = false)
    private boolean root;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Folder parent;

    @ManyToOne
    @JoinColumn(name = "application_id", nullable = false)
    private Application application;

    @OneToMany(mappedBy = "parent")
    private Set<Folder> children;

    @OneToMany(mappedBy = "folder")
    private Set<File> files;
}
