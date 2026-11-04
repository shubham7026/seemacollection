package com.seemacollection.seemacollection.dto;

import lombok.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CheckoutResponseDTO {
    private ProductDTO product;
    private Double totalAmount;
}
