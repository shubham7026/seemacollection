package com.seemacollection.seemacollection.datamodel;


import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class CheckoutResponse {
    private Product product;
    private Double totalAmount;
}
