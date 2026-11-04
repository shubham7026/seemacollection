package com.seemacollection.seemacollection.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Integer id;
    private String name;
    private String description;
    private Double price;
    private Long stockQuantity;
    private String category;

    public ProductDTO (String name, String description, Double price, Long stockQuantity, String category){
        this(null,name,description,price,stockQuantity,category);
    }
}
