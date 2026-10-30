package com.seemacollection.seemacollection.mapper;

import com.seemacollection.seemacollection.datamodel.User;
import com.seemacollection.seemacollection.dto.FactoryDTO;
import com.seemacollection.seemacollection.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataModelToDTOMapper {

    @Autowired
    private FactoryDTO factoryDTO;

    public UserDTO mapUserToUserDTO(User user){
        UserDTO userDTO =  factoryDTO.getUserDTO();
        userDTO.setLastName(user.getLastName());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        return userDTO;
    }
}
