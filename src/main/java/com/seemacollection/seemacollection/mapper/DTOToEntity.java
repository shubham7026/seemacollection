package com.seemacollection.seemacollection.mapper;

import com.seemacollection.seemacollection.dto.AddressDTO;
import com.seemacollection.seemacollection.dto.ProductDTO;
import com.seemacollection.seemacollection.dto.UserDTO;
import com.seemacollection.seemacollection.entity.Address;
import com.seemacollection.seemacollection.entity.AddressCK;
import com.seemacollection.seemacollection.h2Entity.ProductEntity;
import com.seemacollection.seemacollection.entity.UserEntity;
import com.seemacollection.seemacollection.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Slf4j
public class DTOToEntity {

    public ProductEntity mapProductDto(ProductDTO productDTO) {
        ProductEntity product = new ProductEntity();
        product.setId(productDTO.getId());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setCategory(productDTO.getCategory());
        product.setStockQuantity(productDTO.getStockQuantity());
        product.setName(productDTO.getName());
        return product;
    }

    public UserEntity mapUserDto(UserDTO userDTO) {
        log.info("Mapping userDto to entity: {}", userDTO);
        UserEntity userEntity = new UserEntity();
        userEntity.setId(UserRepository.idGenerator(userDTO.getEmail()));
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setPassword(userDTO.getPassword());
        userEntity.setLastName(userDTO.getLastName());
        userEntity.setPoints(userDTO.getPoints());
        userEntity.setFirstName(userDTO.getFirstName());

        if (userDTO.getAddressDTO() != null && !userDTO.getAddressDTO().isEmpty()){
            userEntity.setAddresses(new ArrayList<>());
            userDTO.getAddressDTO().forEach(
                    add-> {
                        Address address = new Address();
                        address.setAddressCK(new AddressCK(add.getTehsil(), add.getPlotNo()));
                        address.setCity(add.getCity());
                        address.setPin(add.getPin());
                        address.setState(add.getState());
                        userEntity.getAddresses().add(address);
                    }
            );
        }
        return userEntity;
    }
}
