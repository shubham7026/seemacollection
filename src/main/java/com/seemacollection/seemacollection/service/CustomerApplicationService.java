package com.seemacollection.seemacollection.service;

import com.seemacollection.seemacollection.dto.AddressDTO;
import com.seemacollection.seemacollection.dto.CustomerDTO;
import com.seemacollection.seemacollection.exception.EntityNotFoundException;
import com.seemacollection.seemacollection.exception.UserAlreadyExists;
import com.seemacollection.seemacollection.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class CustomerApplicationService {

    @Autowired
    private CustomerRepository customerRepository;

    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {

        CustomerDTO cus = customerRepository.getCustomer(customerDTO.getEmail(),customerDTO.getPhone_number());
        if (cus!=null){
            throw  new UserAlreadyExists("User already Exists", HttpStatus.BAD_REQUEST);
        }else{
            customerDTO.setNewCustomer(true);
        }

        return customerRepository.addCustomer(customerDTO);
    }

    public void saveAddress(String email, String phoneNumber, AddressDTO addressDTO) {
        CustomerDTO cus = customerRepository.getCustomer(email,phoneNumber);
        if (cus == null){
            throw new EntityNotFoundException("Entity not found", HttpStatus.NOT_FOUND);
        }
        cus.getAddressDTOList().add(addressDTO);
        customerRepository.addAddress(cus);
    }
}
