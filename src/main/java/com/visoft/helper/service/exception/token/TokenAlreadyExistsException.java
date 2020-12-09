package com.visoft.helper.service.exception.token;

import com.visoft.helper.service.exception.BadRequestException;

public class TokenAlreadyExistsException extends BadRequestException {

    public TokenAlreadyExistsException() {
        super("Token already exists");
    }
}
