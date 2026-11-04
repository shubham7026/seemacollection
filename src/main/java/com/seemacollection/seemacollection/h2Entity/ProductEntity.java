package com.seemacollection.seemacollection.h2Entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Getter
@Setter
@ToString
@Entity
//@Table(name = "products", schema = "inventory", uniqueConstraints = {
//        @UniqueConstraint(columnNames = {"category"})
//})
@Table(name = "products", schema = "inventory")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "productEntityCache")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "productEntityIdGenerator" )
    @SequenceGenerator(name = "productEntityIdGenerator", sequenceName = "idGenerator" , initialValue = 100, allocationSize = 5)
    private Integer id;
    private String name;
    private String description;
    private Double price;
    private Long stockQuantity;
    private String category;
}
