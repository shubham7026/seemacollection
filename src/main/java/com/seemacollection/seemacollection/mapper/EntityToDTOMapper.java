package com.seemacollection.seemacollection.mapper;

import com.seemacollection.seemacollection.dto.FactoryDTO;
import com.seemacollection.seemacollection.dto.UserDTO;
import com.seemacollection.seemacollection.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
        return userDTO;

    }
}
