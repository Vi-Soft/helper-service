package com.visoft.helper.service.persistance.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
public class File extends IdEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String path;

    @OneToOne()
    @JoinColumn(name = "folder_id", nullable = false)
    private Folder folder;
}
