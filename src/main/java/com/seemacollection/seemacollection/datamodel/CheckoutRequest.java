package com.seemacollection.seemacollection.datamodel;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class CheckoutRequest {
    private String emailId;
    private Integer productId;
    private Long quantity;
    private String phone_number;
}
