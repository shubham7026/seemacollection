package com.seemacollection.seemacollection.dto;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Getter
@Component
public class FactoryDTO {

    @Autowired
    private UserDTO userDTO;

    @Autowired
    private AddressDTO addressDTO;

    @Autowired
    private ProductDTO productDTO;

}
