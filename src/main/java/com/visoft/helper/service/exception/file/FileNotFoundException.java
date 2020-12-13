package com.visoft.helper.service.exception.file;

import com.visoft.helper.service.exception.NotFoundException;

public class FileNotFoundException extends NotFoundException {

    public FileNotFoundException() {
        super("File not found");
    }
}
