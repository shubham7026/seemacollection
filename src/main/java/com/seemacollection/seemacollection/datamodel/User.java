package com.seemacollection.seemacollection.datamodel;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public class User {
    final private String firstName;
    final private String lastName;
    final private String email;
    final private String password;
}
