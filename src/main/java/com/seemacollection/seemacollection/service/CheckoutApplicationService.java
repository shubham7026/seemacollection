package com.seemacollection.seemacollection.service;

import com.seemacollection.seemacollection.dto.*;
import com.seemacollection.seemacollection.exception.AbstractException;
import com.seemacollection.seemacollection.exception.UserNotFoundException;
import com.seemacollection.seemacollection.mapper.DTOToDataModelMapper;
import com.seemacollection.seemacollection.mapper.DTOToEntity;
import com.seemacollection.seemacollection.repository.CustomerRepository;
import com.seemacollection.seemacollection.repository.ProductRepository;
import com.seemacollection.seemacollection.repository.UpdateDbAfterCheckout;
import com.seemacollection.seemacollection.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class CheckoutApplicationService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DTOToDataModelMapper dtoToDataModelMapper;

    @Autowired
    private UpdateDbAfterCheckout updateDbAfterCheckout;

    @Autowired
    private DTOToEntity dtoToEntity;

    @Autowired
    private CustomerRepository customerRepository;

    public CheckoutResponseDTO checkout(CheckoutRequestDTO checkoutRequest) {

        CustomerDTO customerDTO = customerRepository.getCustomer(checkoutRequest.getEmailId(),checkoutRequest.getPhone_number());
        if (customerDTO == null){
            throw new UserNotFoundException("User Not Found with id: "+ checkoutRequest.getEmailId() + "and phone_number: " + checkoutRequest.getPhone_number(),
                    HttpStatus.NOT_FOUND
                    );
        }


        Integer productId = checkoutRequest.getProductId();
        ProductDTO productDTO = productRepository.getProductById(productId);

        if (customerDTO.getProductId()!=null){
            throw new AbstractException("User already bought : " + productDTO.getName(), HttpStatus.BAD_REQUEST);
        }

        if (productDTO ==null || checkoutRequest.getQuantity() > productDTO.getStockQuantity()){
            throw new AbstractException("Stocks is not available", HttpStatus.BAD_REQUEST);
        }

        Double totalAmount = getTotalAMount(checkoutRequest, customerDTO, productDTO);

        customerDTO.setProductId(productId);
        customerDTO.setProductPrice(productDTO.getPrice());
        customerDTO.setTotalAmount(totalAmount);
        customerDTO.setQuantity(checkoutRequest.getQuantity());
        productDTO.setStockQuantity(productDTO.getStockQuantity() - checkoutRequest.getQuantity());

        CheckoutResponseDTO checkoutResponse = new CheckoutResponseDTO();
        checkoutResponse.setProduct(productDTO);
        checkoutResponse.setTotalAmount(totalAmount);

        updateDbAfterCheckout.updatePointsAndQuantity(customerDTO,productDTO);

        return checkoutResponse;

    }

    private  Double getTotalAMount(CheckoutRequestDTO checkoutRequest, CustomerDTO customerDTO, ProductDTO productDTO) {


        Long quantity = checkoutRequest.getQuantity();
        Double price = productDTO.getPrice();
        Double totalAmount = quantity * price ;
        return totalAmount;
    }
}
