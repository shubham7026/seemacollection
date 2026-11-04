package com.seemacollection.seemacollection.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "users")
@Setter
@Getter
@ToString
public class UserEntity {

    @Id
    private String id;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private Double points;
    private boolean login;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userEntity")
//    @JoinColumns(value = {
//            @JoinColumn(referencedColumnName = "plotNo"),
//            @JoinColumn(referencedColumnName = "tehsil")
//    })
    private List<Address> addresses;

}
