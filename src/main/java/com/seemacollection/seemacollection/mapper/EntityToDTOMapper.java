package com.seemacollection.seemacollection.mapper;

import com.seemacollection.seemacollection.dto.*;
import com.seemacollection.seemacollection.entity.Address;
import com.seemacollection.seemacollection.entity.Customer;
import com.seemacollection.seemacollection.entity.CustomerCK;
import com.seemacollection.seemacollection.h2Entity.ProductEntity;
import com.seemacollection.seemacollection.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;

@Component
public class EntityToDTOMapper {

    @Autowired
    private FactoryDTO factoryDTO;

    public UserDTO mapUserEntityToUserDTO(UserEntity userEntity){

        if (userEntity == null){
            return null;
        }

        UserDTO userDTO =  factoryDTO.getUserDTO();
        userDTO.setFirstName(userEntity.getFirstName());
        userDTO.setLastName(userEntity.getLastName());
        userDTO.setEmail(userEntity.getEmail());
        userDTO.setPassword(userEntity.getPassword());
        userDTO.setPoints(userEntity.getPoints());

        if (userEntity.getAddresses() != null && !userEntity.getAddresses().isEmpty()){
            userDTO.setAddressDTO(new ArrayList<>());
            userEntity.getAddresses().forEach(
                    add-> {
                        AddressDTO addressDTO = AddressDTO.builder()
                                .city(add.getCity())
                                .pin(add.getPin())
                                .state(add.getState())
                                .plotNo(add.getAddressCK().getPlotNo())
                                .tehsil(add.getAddressCK().getTehsil())
                                .build();
                        userDTO.getAddressDTO().add(addressDTO);
                    }
            );
        }

        return userDTO;

    }

    public ProductDTO mapProductEntityToDto(ProductEntity productEntity) {
        if (productEntity == null){
            return null ;
        }

        ProductDTO productDTO = factoryDTO.getProductDTO();
        productDTO.setId(productEntity.getId());
        productDTO.setDescription(productEntity.getDescription());
        productDTO.setName(productEntity.getName());
        productDTO.setCategory(productEntity.getCategory());
        productDTO.setPrice(productEntity.getPrice());
        productDTO.setStockQuantity(productEntity.getStockQuantity());
        productDTO.setName(productEntity.getName());
        return productDTO;
    }
}
