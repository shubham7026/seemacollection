package com.seemacollection.seemacollection.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(schema = "user")
@Setter
@Getter
public class UserEntity {

    @Id
    private String id;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private int points;

}
