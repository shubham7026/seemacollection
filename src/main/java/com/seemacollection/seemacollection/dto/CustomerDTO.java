package com.seemacollection.seemacollection.dto;


import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder

public class CustomerDTO {
    private String name;
    private String email;
    private String phone_number;
    private Integer productId;
    private Long quantity;
    private Double productPrice;
    private Double totalAmount;
    private boolean isNewCustomer;
    private List<AddressDTO> addressDTOList = new ArrayList<>();
}
