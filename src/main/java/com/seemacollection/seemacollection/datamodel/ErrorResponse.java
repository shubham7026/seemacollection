package com.seemacollection.seemacollection.datamodel;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Builder
@Getter
public class ErrorResponse {
    private String errorMessage;
    private int status;
    private Date timestamp;
    private String path;
}
