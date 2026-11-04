package com.seemacollection.seemacollection.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Entity
@Getter
@Setter
@Table(name = "signUp_counts")
public class SignUpCountEntity {

    private int noOfSignUp;

    @Id
    private Date date;
}
