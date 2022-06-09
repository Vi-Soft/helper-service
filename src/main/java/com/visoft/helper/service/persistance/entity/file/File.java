package com.visoft.helper.service.persistance.entity.file;

import com.visoft.helper.service.persistance.entity.Application;
import com.visoft.helper.service.persistance.entity.Folder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class File extends PathMultiLanguage {

    @ManyToOne
    @JoinColumn(name = "folder_id")
    private Folder folder;

    @ManyToOne
    @JoinColumn(name = "application_id", nullable = false)
    private Application application;
}
