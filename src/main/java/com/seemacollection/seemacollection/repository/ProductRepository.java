package com.seemacollection.seemacollection.repository;

import com.seemacollection.seemacollection.h2dao.ProductDao;
import com.seemacollection.seemacollection.dto.ProductDTO;
import com.seemacollection.seemacollection.h2Entity.ProductEntity;
import com.seemacollection.seemacollection.mapper.DTOToEntity;
import com.seemacollection.seemacollection.mapper.EntityToDTOMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Slf4j
@Repository
public class ProductRepository {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private EntityToDTOMapper entityToDTOMapper;

    @Autowired
    private DTOToEntity dtoToEntity;

   // @Autowired
   // private EntityManager entityManager;


//@Transactional(transactionManager = "h2TransactionManager")
    public ProductDTO getProductById (Integer id){
        Optional<ProductEntity> optionalProductEntity = productDao.findById(id);
        if (optionalProductEntity.isPresent()){
            ProductDTO productDto = entityToDTOMapper.mapProductEntityToDto(optionalProductEntity.get());
            return productDto;
        }else{
            return null;
        }

    }


    public ProductDTO persist(ProductDTO productDTO, boolean flag) {
        ProductEntity product = dtoToEntity.mapProductDto(productDTO);
        ProductEntity savedProduct = productDao.save(product);
        return entityToDTOMapper.mapProductEntityToDto(savedProduct);
    }
}
