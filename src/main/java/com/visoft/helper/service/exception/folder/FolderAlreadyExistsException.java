package com.visoft.helper.service.exception.folder;

import com.visoft.helper.service.exception.BadRequestException;

public class FolderAlreadyExistsException extends BadRequestException {

    public FolderAlreadyExistsException() {
        super("Folder already exists");
    }
}
