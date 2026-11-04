package com.seemacollection.seemacollection.datamodel;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
public class User {
     private String firstName;
     private String lastName;
     private String email;
     private String password;
     private Double points;
     private List<Address> address;
}
