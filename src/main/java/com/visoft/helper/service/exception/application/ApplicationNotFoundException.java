package com.visoft.helper.service.exception.application;

import com.visoft.helper.service.exception.NotFoundException;

public class ApplicationNotFoundException extends NotFoundException {

    public ApplicationNotFoundException() {
        super("Application not found");
    }
}
