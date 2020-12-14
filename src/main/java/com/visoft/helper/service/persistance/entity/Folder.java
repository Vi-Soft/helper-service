package com.visoft.helper.service.persistance.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Folder extends OrderNumberEntity {

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Folder parent;

    @ManyToOne
    @JoinColumn(name = "application_id", nullable = false)
    private Application application;

    @OneToMany(mappedBy = "parent")
    private List<Folder> children;

    @OneToMany(mappedBy = "folder")
    private List<File> files;
}
