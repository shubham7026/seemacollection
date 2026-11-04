package com.seemacollection.seemacollection.service;

import com.seemacollection.seemacollection.exception.MissingMandatoryField;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class MandatoryFieldValidatorService {
    public void validateProductId(Integer productId) {
        if (productId==null ){
            throw new MissingMandatoryField("productId should not be blank", HttpStatus.BAD_REQUEST);
        }
    }
}
