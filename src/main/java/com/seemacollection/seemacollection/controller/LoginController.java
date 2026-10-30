package com.seemacollection.seemacollection.controller;

import com.seemacollection.seemacollection.datamodel.User;
import com.seemacollection.seemacollection.dto.UserDTO;
import com.seemacollection.seemacollection.mapper.DataModelToDTOMapper;
import com.seemacollection.seemacollection.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/seemacollection")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private DataModelToDTOMapper dataModelToDTOMapper;

    @PostMapping(path = "/login")
    public ResponseEntity<String> login(@RequestParam("emailId") String emailId, @RequestParam("password") String password){
        try{
            loginService.login(emailId, password);
        }catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();

    }

    @PostMapping(value = "/signUp")
    @ResponseBody
    public ResponseEntity<String> signUp (@RequestBody User user){
        validateUser(user);
        UserDTO userDTO = dataModelToDTOMapper.mapUserToUserDTO(user);
        try{
            loginService.signUp(userDTO);
        }catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("User created");
    }

    private void validateUser(User user) {
        if (user!=null &&
                user.getEmail()!=null &&
                user.getPassword()!=null &&
                user.getFirstName()!=null &&
                user.getLastName()!= null){
            return;
        }
        throw new RuntimeException("Missing Mandatory Fields");
    }
}
