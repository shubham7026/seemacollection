package com.seemacollection.seemacollection.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressDTO {
    private String city;
    private String state;
    private String pin;
    private String tehsil;
    private int plotNo;
}
