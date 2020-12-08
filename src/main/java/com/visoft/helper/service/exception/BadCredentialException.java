package com.visoft.helper.service.exception;

public class BadCredentialException extends BadRequestException {

    public BadCredentialException() {
        super("Bad Credentials");
    }
}
