package com.seemacollection.seemacollection.service;

import com.seemacollection.seemacollection.datamodel.Product;
import com.seemacollection.seemacollection.dto.ProductDTO;
import com.seemacollection.seemacollection.exception.EntityNotFoundException;
import com.seemacollection.seemacollection.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository ;

    public ProductDTO getProductById(Integer productId) {
        ProductDTO productDTO = productRepository.getProductById(productId);
        if (productDTO == null){
            throw new EntityNotFoundException("product not found by id : " + productId, HttpStatus.NOT_FOUND);
        }
        return productDTO;
    }

    public ProductDTO persistProduct(ProductDTO productDTO,boolean flag) {
        ProductDTO p =  productRepository.persist(productDTO, flag);
        return p;
    }
}
