package com.seemacollection.seemacollection.controller;

import com.seemacollection.seemacollection.datamodel.Address;
import com.seemacollection.seemacollection.datamodel.Customer;
import com.seemacollection.seemacollection.dto.AddressDTO;
import com.seemacollection.seemacollection.dto.CustomerDTO;
import com.seemacollection.seemacollection.mapper.DTOToDataModelMapper;
import com.seemacollection.seemacollection.mapper.DataModelToDTOMapper;
import com.seemacollection.seemacollection.repository.CustomerRepository;
import com.seemacollection.seemacollection.service.CustomerApplicationService;
import com.seemacollection.seemacollection.service.CustomerValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/seemacollection")
public class CustomerController {

    @Autowired
    private CustomerValidationService customerValidationService;

    @Autowired
    private CustomerApplicationService customerApplicationService;

    @Autowired
    private DataModelToDTOMapper dataModelToDTOMapper;

    @Autowired
    private DTOToDataModelMapper dtoToDataModelMapper;

    @Autowired
    private CustomerRepository customerRepository;


    @GetMapping("/customer")
    public ResponseEntity<CustomerDTO> getCustomer (@RequestParam String email, @RequestParam String phone_number){
        CustomerDTO customer = customerRepository.getCustomer(email,phone_number);
        return ResponseEntity.status(HttpStatus.OK).body(customer);
    }

    @PostMapping("/customer")
    public ResponseEntity<Customer> addCustomer (@RequestBody Customer customer){
        customerValidationService.validate(customer);
        CustomerDTO customerDTO = customerApplicationService.saveCustomer(dataModelToDTOMapper.mapCustomer(customer));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(dtoToDataModelMapper.mapCustomer(customerDTO));
    }

    @PostMapping("/addAddress")
    public ResponseEntity<Address> saveAddress (@RequestParam(name = "email") String email,
                                                @RequestParam(name = "phone_number") String phone_number, @RequestBody Address address){
        AddressDTO addressDTO = dataModelToDTOMapper.mapAddress(address);
        customerApplicationService.saveAddress(email,phone_number,addressDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(address);
    }
}
