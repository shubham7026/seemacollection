package com.seemacollection.seemacollection.mapper;

import com.seemacollection.seemacollection.datamodel.*;
import com.seemacollection.seemacollection.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@Slf4j
public class DataModelToDTOMapper {

    @Autowired
    private FactoryDTO factoryDTO;

    public UserDTO mapUserToUserDTO(User user){
        UserDTO userDTO =  factoryDTO.getUserDTO();
        userDTO.setLastName(user.getLastName());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());

        if (user.getAddress()!=null && !user.getAddress().isEmpty()){
            userDTO.setAddressDTO(new ArrayList<>());
            user.getAddress().forEach(
                    add-> {
                        AddressDTO addressDTO = AddressDTO.builder()
                                .city(add.getCity())
                                .plotNo(add.getPlotNo())
                                .state(add.getState())
                                .pin(add.getPin())
                                .tehsil(add.getTehsil())
                                .build();
                        userDTO.getAddressDTO().add(addressDTO);

                    }
            );
        }
        return userDTO;
    }

    public ProductDTO mapProduct(Product product) {
        ProductDTO productDTO = factoryDTO.getProductDTO();
        productDTO.setId(product.getId());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setCategory(product.getCategory());
        productDTO.setStockQuantity(product.getStockQuantity());
        productDTO.setName(product.getName());
        return productDTO;
    }

    public CustomerDTO mapCustomer(Customer customer) {
        log.info("Mapping customer to dto: {}", customer);
        CustomerDTO customerDTO  = CustomerDTO.builder()
                .email(customer.getEmail())
                .name(customer.getName())
                .phone_number(customer.getPhone_number())
                .build();

        log.info("Mapped dto: {}", customerDTO);
        return customerDTO;
    }

    public CheckoutRequestDTO mapCheckoutRequest(CheckoutRequest checkoutRequest) {
        if (checkoutRequest == null ){
            return null;
        }

        return CheckoutRequestDTO
                .builder()
                .emailId(checkoutRequest.getEmailId())
                .phone_number(checkoutRequest.getPhone_number())
                .productId(checkoutRequest.getProductId())
                .quantity(checkoutRequest.getQuantity())
                .build();
    }

    public AddressDTO mapAddress(Address address) {
        if (address != null){
            AddressDTO addressDTO = AddressDTO.builder()
                    .tehsil(address.getTehsil())
                    .pin(address.getPin())
                    .state(address.getState())
                    .plotNo(address.getPlotNo())
                    .city(address.getCity())
                    .build();
            return addressDTO;
        }
        return null;
    }
}
