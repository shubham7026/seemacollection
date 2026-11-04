package com.seemacollection.seemacollection.dto;


import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckoutRequestDTO {
    private String emailId;
    private Integer productId;
    private Long quantity;
    private String phone_number;
}
