package com.visoft.helper.service.exception.application;

import com.visoft.helper.service.exception.BadRequestException;

public class ApplicationAlreadyExistsException extends BadRequestException {

    public ApplicationAlreadyExistsException() {
        super("Application already exists");
    }
}
