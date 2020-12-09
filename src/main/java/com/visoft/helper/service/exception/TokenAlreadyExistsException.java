package com.visoft.helper.service.exception;

public class TokenAlreadyExistsException extends BadRequestException {

    public TokenAlreadyExistsException() {
        super("Token already exists");
    }
}
