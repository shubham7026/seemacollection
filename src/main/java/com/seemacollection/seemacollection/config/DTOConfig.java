package com.seemacollection.seemacollection.config;

;
import com.seemacollection.seemacollection.dto.AddressDTO;
import com.seemacollection.seemacollection.dto.ProductDTO;
import com.seemacollection.seemacollection.dto.UserDTO;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.ArrayList;

@Configuration
public class DTOConfig {

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public UserDTO userDTO (){
        UserDTO userDTO = new UserDTO();
        userDTO.setAddressDTO(new ArrayList<>());
        return userDTO;
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public ProductDTO productDTO(){
        return new ProductDTO();
    }

    @Bean
    @Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public AddressDTO addressDTO(){
        return new AddressDTO();
    }
}
