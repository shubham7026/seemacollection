package com.seemacollection.seemacollection.service;

import com.seemacollection.seemacollection.exception.EntityNotFoundException;
import com.seemacollection.seemacollection.repository.UserRepository;
import com.seemacollection.seemacollection.dto.UserDTO;
import com.seemacollection.seemacollection.exception.UserAlreadyExists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    @Autowired(required = false)
    private RewardPointService rewardPointService; ;

    public void login (String emailId, String pass){
       UserDTO user =  userRepository.getUserById(emailId);
       if (user!=null && pass.equals(user.getPassword())){
           userRepository.loginUser(user.getEmail());
           return;
       }
       throw new EntityNotFoundException("Invalid username or password", HttpStatus.BAD_REQUEST);
    }


    public void signUp(UserDTO userDTO) {
        UserDTO existUser = userRepository.getUserById(userDTO.getEmail());
        if (existUser == null){
            if (rewardPointService!=null){
                userDTO.setPoints(rewardPointService.getPoints());
            }
            userRepository.save(userDTO);
            return;
        }
        throw new UserAlreadyExists("user with email: "+ userDTO.getEmail() + " already exists", HttpStatus.BAD_REQUEST);
    }

    public UserDTO getUserById(String emailId) {
        UserDTO user =  userRepository.getUserById(emailId);
        if (user == null){
            throw new EntityNotFoundException("User with email: "+ emailId + " not found", HttpStatus.BAD_REQUEST );
        }
        return user;
    }
}
