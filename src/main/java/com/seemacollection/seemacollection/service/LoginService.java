package com.seemacollection.seemacollection.service;

import com.seemacollection.seemacollection.repository.UserRepository;
import com.seemacollection.seemacollection.dto.UserDTO;
import com.seemacollection.seemacollection.exception.UserAlreadyExists;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    @Autowired(required = false)
    private RewardPointService rewardPointService; ;

    public void login (String emailId, String pass){
       UserDTO user =  userRepository.getUserById(emailId);
       if (user!=null && pass.equals(user.getPassword())){
           // do login
           return;
       }
       throw new EntityNotFoundException("Invalid username or password" + emailId);
    }

    public void signUp(UserDTO userDTO) {
        if (!userRepository.isUserPresent(userDTO)){
            if (rewardPointService!=null){
                userDTO.setPoints(rewardPointService.getPoints());
            }
            userRepository.save(userDTO);
            return;
        }
        throw new UserAlreadyExists("user with email: "+ userDTO.getEmail() + " already exists");
    }
}
