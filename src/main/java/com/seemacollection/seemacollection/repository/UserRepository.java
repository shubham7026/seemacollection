package com.seemacollection.seemacollection.repository;

import com.seemacollection.seemacollection.dto.UserDTO;
import com.seemacollection.seemacollection.entity.UserEntity;
import com.seemacollection.seemacollection.mapper.EntityToDTOMapper;
import com.seemacollection.seemacollection.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Repository
public class UserRepository {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private EntityToDTOMapper entityToDTOMapper;

    public UserDTO getUserById(String emailId){
        return userDAO.findById(idGenerator(emailId))
                .map(userEntity -> entityToDTOMapper.mapUserEntityToUserDTO(userEntity))
                .orElse(null);
    }

    public boolean isUserPresent (UserDTO userDTO){
        Optional<UserEntity> foundUser = userDAO.findById(idGenerator(userDTO.getEmail()));
        if (foundUser.isPresent()){
            return true;
        }
        return false;
    }

    public void save(UserDTO user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(user.getPassword());
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setId(idGenerator(user.getEmail()));
        userEntity.setPoints(user.getPoints());
        userDAO.save(userEntity);
    }

    private String idGenerator(String emailID){
        String generatedKey="";
        try {
            MessageDigest md = MessageDigest.getInstance("SHA1");
            md.update(emailID.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++){
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedKey = sb.toString().substring(0, 8);
        }
        catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return generatedKey;
    }
}
