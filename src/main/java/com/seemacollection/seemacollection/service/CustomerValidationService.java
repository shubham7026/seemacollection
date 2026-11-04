package com.seemacollection.seemacollection.service;

import com.seemacollection.seemacollection.datamodel.Customer;
import com.seemacollection.seemacollection.exception.MissingMandatoryField;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CustomerValidationService {
    public void validate (Customer customer){
        log.info("Validating customer: {}", customer);

        if (customer == null){
            throwException("customer");
        }
        if (customer.getEmail() == null || customer.getEmail().isBlank()){
            throwException("customer.email");
        }

        if (customer.getPhone_number() == null || customer.getPhone_number().isBlank()){
            throwException("customer.phone_number");
        }

        if (customer.getName() == null || customer.getName().isBlank()){
            throwException("customer.name");
        }
        log.info("Validation completed: {}", customer);
    }

    private void throwException (String errorMessage){
        String err = errorMessage + " is null or empty";
        throw new MissingMandatoryField(err, HttpStatus.BAD_REQUEST);
    }
}
