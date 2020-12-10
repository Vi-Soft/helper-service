package com.visoft.helper.service.exception.application;

import com.visoft.helper.service.exception.BadRequestException;

public class ApplicationAlreadyHasRootFolderException extends BadRequestException {

    public ApplicationAlreadyHasRootFolderException() {
        super("Application already has root folder");
    }
}
