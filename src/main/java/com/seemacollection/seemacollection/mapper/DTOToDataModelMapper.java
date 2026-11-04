package com.seemacollection.seemacollection.mapper;

import com.seemacollection.seemacollection.datamodel.*;
import com.seemacollection.seemacollection.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DTOToDataModelMapper {

    @Autowired
    private FactoryDTO factoryDTO;



    public Product mapProductDTO(ProductDTO productDTO) {
        Product product = new Product(productDTO.getId(),productDTO.getName(), productDTO.getDescription(), productDTO.getPrice(), productDTO.getStockQuantity(), productDTO.getCategory());
        return product;
    }

    public User mapUserDTO(UserDTO userDto) {
        return User.builder()
                .email(userDto.getEmail())
                .points(userDto.getPoints())
                .address(mapAddress(userDto.getAddressDTO()))
                .build();
    }

    private List<Address> mapAddress (List<AddressDTO> addressDTOReqs){

        if (addressDTOReqs!=null && !addressDTOReqs.isEmpty()){
            List<Address> addresses = new ArrayList<>();
            addressDTOReqs.forEach(addressDTOReq->{
                Address address = new Address();
                address.setCity(addressDTOReq.getCity());
                address.setPin(addressDTOReq.getPin());
                address.setTehsil(addressDTOReq.getTehsil());
                address.setState(addressDTOReq.getState());
                address.setPlotNo(addressDTOReq.getPlotNo());
                addresses.add(address);
            });
            return addresses;
        }
        return new ArrayList<>();
    }

    public Customer mapCustomer(CustomerDTO customerDTO) {
        return Customer.builder()
                .name(customerDTO.getName())
                .phone_number(customerDTO.getPhone_number())
                .email(customerDTO.getEmail())
                .build();
    }

    public CheckoutResponse mapCheckoutResponse(CheckoutResponseDTO checkoutResponseDTO) {
        if (checkoutResponseDTO == null){
            return null;
        }

        CheckoutResponse checkoutResponse = CheckoutResponse.builder()
                .totalAmount(checkoutResponseDTO.getTotalAmount())
                .product(mapProductDTO(checkoutResponseDTO.getProduct()))
                .build();
        return checkoutResponse;
    }
}
