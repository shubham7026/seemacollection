package com.seemacollection.seemacollection.datamodel;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class Customer {
    private String name;
    private String phone_number;
    private String email;
}
