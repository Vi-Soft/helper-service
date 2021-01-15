package com.visoft.helper.service.exception;

import org.springframework.http.HttpStatus;

public class InternalServerError extends CustomException {

    public InternalServerError(String message) {
        super(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
