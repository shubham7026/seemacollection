package com.seemacollection.seemacollection.h2dao;

import com.seemacollection.seemacollection.h2Entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface ProductDao extends JpaRepository<ProductEntity, Integer> {


    @Transactional
    @Modifying
    @Query("UPDATE ProductEntity product SET product.stockQuantity = :stockQuantity WHERE product.id = :id")
    void updateQuantityById(Integer id, Long stockQuantity);
}
