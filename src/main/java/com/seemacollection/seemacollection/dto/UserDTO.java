package com.seemacollection.seemacollection.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor

public class UserDTO {
     private String firstName;
     private String email;
     private String password;
     private String lastName;
     private int points;
}
