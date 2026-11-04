package com.seemacollection.seemacollection.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Table(name = "address")
@Entity
@Getter
@Setter
@ToString
public class Address {

    //@Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String city;
    private String state;
    private String pin;

    @EmbeddedId
    private AddressCK addressCK;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;
}
