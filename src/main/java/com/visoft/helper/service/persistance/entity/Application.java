package com.visoft.helper.service.persistance.entity;

import com.visoft.helper.service.persistance.entity.file.File;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
public class Application extends MultiLanguageName {

    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "application")
    private List<Folder> folders;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "application")
    private List<File> files = new ArrayList<>();

    public List<Folder> getRootFolders() {
        folders = folders == null ? new ArrayList<>() : folders;
        return this.folders.stream()
                .filter(folder -> folder.getParent() == null)
                .collect(Collectors.toList()
                );
    }
}
