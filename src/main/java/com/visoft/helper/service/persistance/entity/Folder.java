package com.visoft.helper.service.persistance.entity;

import com.visoft.helper.service.persistance.entity.file.File;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
public class Folder extends OrderNumber {

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Folder parent;

    @ManyToOne
    @JoinColumn(name = "application_id", nullable = false)
    private Application application;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "parent")
    private List<Folder> children;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "folder")
    private List<File> files;
}
