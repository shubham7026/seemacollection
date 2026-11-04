package com.seemacollection.seemacollection.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.springframework.data.domain.Persistable;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "customer")
@AllArgsConstructor
@NoArgsConstructor
public class Customer implements Persistable<CustomerCK> {

    @EmbeddedId
    private CustomerCK customerCK;
    private String name;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id")
    private CustomerProductDetails customerProductDetails;


//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(referencedColumnName = "")
//    private List<Address> addresses;

    @Override
    public CustomerCK getId() {
        return customerCK;
    }

    @Override
    public boolean isNew() {
        return customerProductDetails==null;
    }
}
