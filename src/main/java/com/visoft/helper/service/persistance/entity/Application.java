package com.visoft.helper.service.persistance.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
public class Application extends IdEntity {

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "application")
    private List<Folder> folders;

    public List<Folder> getRootFolders() {
        return this.folders.stream()
                .filter(folder -> folder.getParent() == null)
                .collect(Collectors.toList()
                );
    }
}
