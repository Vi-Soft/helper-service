package com.visoft.helper.service.persistance.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
public class Application extends MultiLanguageNameEntity {

    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "application")
    private List<Folder> folders;

    public List<Folder> getRootFolders() {
        return this.folders.stream()
                .filter(folder -> folder.getParent() == null)
                .collect(Collectors.toList()
                );
    }
}
