package com.visoft.helper.service.persistance.entity.file;

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
    @JoinColumn(name = "folder_id", nullable = false)
    private Folder folder;
}
