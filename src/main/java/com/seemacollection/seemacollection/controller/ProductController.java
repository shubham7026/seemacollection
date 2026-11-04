package com.seemacollection.seemacollection.controller;

import com.seemacollection.seemacollection.datamodel.Product;
import com.seemacollection.seemacollection.dto.ProductDTO;
import com.seemacollection.seemacollection.mapper.DTOToDataModelMapper;
import com.seemacollection.seemacollection.mapper.DataModelToDTOMapper;
import com.seemacollection.seemacollection.service.MandatoryFieldValidatorService;
import com.seemacollection.seemacollection.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/seemacollection")
public class ProductController {

    @Autowired
    private MandatoryFieldValidatorService validatorService;

    @Autowired
    private ProductService productService;

    @Autowired
    private DTOToDataModelMapper dtoToDataModelMapper;

    @Autowired
    private DataModelToDTOMapper dataModelToDTOMapper;

    @PostMapping("/product")
    public ResponseEntity<Product> addProduct (@RequestBody Product product){
        //validatorService.validateProduct(product);
        Long startTime = System.nanoTime();
        product = dtoToDataModelMapper.mapProductDTO(
                productService.persistProduct(
                        dataModelToDTOMapper.mapProduct(product), true
                ));
        Long endTime = System.nanoTime();
        log.info("TimeTaken for post - {}" , (endTime - startTime)/100000);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(product);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<Product> getProductById (@PathVariable("productId") Integer productId){
        Long startTime = System.nanoTime();
        validatorService.validateProductId(productId);
        ProductDTO productDTO = productService.getProductById(productId);
        Product product = dtoToDataModelMapper.mapProductDTO(productDTO);
        Long endTime = System.nanoTime();
        log.info("TimeTaken - {}" , (endTime - startTime)/100000);
        log.info("Product Category: {}", product.getCategory());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(product);
    }
}
