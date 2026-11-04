package com.seemacollection.seemacollection.controller;

import com.seemacollection.seemacollection.datamodel.User;
import com.seemacollection.seemacollection.dto.UserDTO;
import com.seemacollection.seemacollection.exception.MissingMandatoryField;
import com.seemacollection.seemacollection.mapper.DTOToDataModelMapper;
import com.seemacollection.seemacollection.mapper.DataModelToDTOMapper;
import com.seemacollection.seemacollection.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/seemacollection")
@Slf4j
public class UserController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private DataModelToDTOMapper dataModelToDTOMapper;

    @Autowired
    private DTOToDataModelMapper dtoToDataModelMapper;


    @GetMapping("/user/{emailId}")
    public ResponseEntity<User> getUserByEmailId(@PathVariable("emailId") String emailId){
        if (emailId==null || emailId.isBlank()){
            throw new MissingMandatoryField("EmailId is empty or blank", HttpStatus.BAD_REQUEST);
        }

        UserDTO userDto = loginService.getUserById(emailId);
        User user = dtoToDataModelMapper.mapUserDTO(userDto);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(user);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<String> login(@RequestParam("emailId") String emailId, @RequestParam("password") String password){
        loginService.login(emailId,password);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();

    }

    @PostMapping(value = "/signUp")
    public ResponseEntity<String> signUp (@RequestBody User user){
        validateUser(user);
        UserDTO userDTO = dataModelToDTOMapper.mapUserToUserDTO(user);
        loginService.signUp(userDTO);
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
        log.error("Mandatory fields are missing");
        throw new MissingMandatoryField("Missing Mandatory Fields", HttpStatus.BAD_REQUEST);
    }
}
