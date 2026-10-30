package com.seemacollection.seemacollection.config;

;
import com.seemacollection.seemacollection.dto.UserDTO;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class DTOConfig {

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public UserDTO userDTO (){
        return new UserDTO();
    }
}
