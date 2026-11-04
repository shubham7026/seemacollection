package com.seemacollection.seemacollection.exception;

import org.springframework.http.HttpStatus;

import java.util.HashMap;

public class UserAlreadyExists extends AbstractException {
    public UserAlreadyExists(String message, HttpStatus httpStatus) {
        super(message,httpStatus);
    }
}
