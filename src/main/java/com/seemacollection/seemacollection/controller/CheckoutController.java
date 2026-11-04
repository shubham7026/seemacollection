package com.seemacollection.seemacollection.controller;


import com.seemacollection.seemacollection.datamodel.CheckoutRequest;
import com.seemacollection.seemacollection.datamodel.CheckoutResponse;
import com.seemacollection.seemacollection.datamodel.ErrorResponse;
import com.seemacollection.seemacollection.dto.CheckoutRequestDTO;
import com.seemacollection.seemacollection.dto.CheckoutResponseDTO;
import com.seemacollection.seemacollection.exception.AbstractException;
import com.seemacollection.seemacollection.exception.MissingMandatoryField;
import com.seemacollection.seemacollection.mapper.DTOToDataModelMapper;
import com.seemacollection.seemacollection.mapper.DataModelToDTOMapper;
import com.seemacollection.seemacollection.service.CheckoutApplicationService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Date;

@RestController
@RequestMapping("/seemacollection")
@Slf4j
public class CheckoutController {

    @Autowired
    private CheckoutApplicationService checkoutService;

    @Autowired
    private DataModelToDTOMapper dataModelToDTOMapper;

    @Autowired
    private DTOToDataModelMapper dtoToDataModelMapper;

    @PostMapping("/checkout")
    public ResponseEntity<CheckoutResponse> checkout (@RequestBody CheckoutRequest checkoutRequest){
        log.info("Checkout Request : {}", checkoutRequest);

        validate (checkoutRequest);

        CheckoutRequestDTO checkoutRequestDTO = dataModelToDTOMapper.mapCheckoutRequest(checkoutRequest);

        CheckoutResponseDTO checkoutResponseDTO = checkoutService.checkout(checkoutRequestDTO);

        CheckoutResponse checkoutResponse = dtoToDataModelMapper.mapCheckoutResponse(checkoutResponseDTO);

        log.info("CheckoutResponse: {}", checkoutResponse);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(checkoutResponse);
    }

    private void validate(CheckoutRequest checkoutRequest){

        if (checkoutRequest.getEmailId()==null || checkoutRequest.getEmailId().isBlank()) {
            throw new MissingMandatoryField("EmailId is missing", HttpStatus.BAD_REQUEST);
        }
        if (checkoutRequest.getQuantity() == null || checkoutRequest.getQuantity().equals(0L) ){
            throw new MissingMandatoryField("Quantity is missing", HttpStatus.BAD_REQUEST);
        }
        if (checkoutRequest.getProductId() == null ){
            throw new MissingMandatoryField("ProductId is missing", HttpStatus.BAD_REQUEST);
        }
        if (checkoutRequest.getPhone_number() == null || checkoutRequest.getPhone_number().isBlank()){
            throw new MissingMandatoryField("phone_number is missing", HttpStatus.BAD_REQUEST);
        }
    }

    @ExceptionHandler({AbstractException.class})
    public ResponseEntity<ErrorResponse> handleCheckoutException (HttpServletRequest httpServletRequest, AbstractException abstractException){
        String path = httpServletRequest.getContextPath()+httpServletRequest.getServletPath();
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(Date.from(Instant.now()))
                .errorMessage(abstractException.getErrorMessage())
                .status(abstractException.getHttpStatus().value())
                .path(path)
                .build();
        return ResponseEntity
                .status(abstractException.getHttpStatus())
                .body(errorResponse);

    }

}
