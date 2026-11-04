package com.seemacollection.seemacollection.datamodel;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Product {
    private Integer id;
    private String name;
    private String description;
    private Double price;
    private Long stockQuantity;
    private String category;
}
