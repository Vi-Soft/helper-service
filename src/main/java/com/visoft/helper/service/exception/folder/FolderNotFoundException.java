package com.visoft.helper.service.exception.folder;

import com.visoft.helper.service.exception.NotFoundException;

public class FolderNotFoundException extends NotFoundException {

    public FolderNotFoundException() {
        super("Folder not found");
    }
}
