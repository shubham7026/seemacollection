package com.seemacollection.seemacollection.exception;

import com.seemacollection.seemacollection.datamodel.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.time.Instant;
import java.util.Date;
@Slf4j
@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(AbstractException.class)
    public ResponseEntity<ErrorResponse> handle (AbstractException exception){
        ErrorResponse errorResponse = ErrorResponse.builder()
                .errorMessage(exception.getErrorMessage())
                .status(exception.getHttpStatus().value())
                .timestamp(Date.from(Instant.now()))
                .build();
        return ResponseEntity
                .status(errorResponse.getStatus())
                .body(errorResponse);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAnyException (Exception exception){
        log.error("erros message: {} {}", exception.getMessage(), exception.getStackTrace() );
        ErrorResponse errorResponse = ErrorResponse.builder()
                .errorMessage(exception.getMessage())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .timestamp(Date.from(Instant.now()))
                .build();
        return ResponseEntity
                .status(errorResponse.getStatus())
                .body(errorResponse);
    }
}
