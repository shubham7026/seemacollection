package com.seemacollection.seemacollection.exception;

import org.springframework.http.HttpStatus;

public class EntityNotFoundException extends AbstractException {
    public EntityNotFoundException(String message, HttpStatus httpStatus) {
        super(message,httpStatus);
    }
}
