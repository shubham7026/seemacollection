package com.seemacollection.seemacollection.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "customer_product_details")
@Builder
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerProductDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer productId;
    private Long quantity;
    private Double productPrice;
    private Double totalAmount;

    @OneToOne(mappedBy = "customerProductDetails", fetch = FetchType.EAGER)
    private Customer customer;
}
