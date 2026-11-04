package com.seemacollection.seemacollection.datamodel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Address {
    private String city;
    private String state;
    private String pin;
    private String tehsil;
    private int plotNo;
}
