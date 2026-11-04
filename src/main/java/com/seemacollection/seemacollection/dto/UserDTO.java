package com.seemacollection.seemacollection.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor

public class UserDTO {
     private String firstName;
     private String email;
     private String password;
     private String lastName;
     private Double points;
     private List<AddressDTO> addressDTO;
}
