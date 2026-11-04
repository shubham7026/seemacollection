package com.seemacollection.seemacollection.exception;

import org.springframework.http.HttpStatus;


public class MissingMandatoryField extends AbstractException {
    public MissingMandatoryField(String errorMessage, HttpStatus httpStatus) {
        super(errorMessage,httpStatus);
    }
}
