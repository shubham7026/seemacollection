package com.seemacollection.seemacollection.exception;


import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AbstractException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final String errorMessage;

    public AbstractException(String message, HttpStatus httpStatus) {
        super(message);
        this.errorMessage = message;
        this.httpStatus = httpStatus;
    }

}
