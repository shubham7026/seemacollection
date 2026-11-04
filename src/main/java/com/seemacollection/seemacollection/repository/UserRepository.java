package com.seemacollection.seemacollection.repository;

import com.seemacollection.seemacollection.dto.UserDTO;
import com.seemacollection.seemacollection.entity.UserEntity;
import com.seemacollection.seemacollection.mapper.DTOToEntity;
import com.seemacollection.seemacollection.mapper.EntityToDTOMapper;
import com.seemacollection.seemacollection.mysqldao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Repository
public class UserRepository {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private EntityToDTOMapper entityToDTOMapper;

    @Autowired
    private DTOToEntity dtoToEntity;



    public UserDTO getUserById(String emailId){
        return userDAO.findById(idGenerator(emailId))
                .map(userEntity -> entityToDTOMapper.mapUserEntityToUserDTO(userEntity))
                .orElse(null);
    }



    public UserDTO save(UserDTO user) {
        UserEntity entity = dtoToEntity.mapUserDto(user);
        UserEntity savedEntity = userDAO.save(entity);
        return entityToDTOMapper.mapUserEntityToUserDTO(savedEntity);
    }

    public static String idGenerator(String emailID){
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

    public void loginUser(String emailId) {
        String id = idGenerator(emailId);
        userDAO.updateLoginById(id);
    }
}
