package com.springboot.productservice.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.productservice.entity.Product;
import com.springboot.productservice.exception.ResourceNotFoundException;
import com.springboot.productservice.exception.ResourceAlreadyExistsException;
import com.springboot.productservice.payload.ProductDto;
import com.springboot.productservice.repository.ProductRepository;
import com.springboot.productservice.services.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Log4j2
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ObjectMapper mapper;

    /**
     * Get All Products from the database.
     * @return All products {@link List<ProductDto>}
     */
    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(product -> mapper.convertValue(product,ProductDto.class)).collect(Collectors.toList());
    }

    /**
     * Get a particular product with productID from the database.
     * @param productId, get product Detail of this productID {@link Long}
     * @return Product Detail {@link ProductDto}
     */
    @Override
    public ProductDto getProductDetail(long productId) {
        Product product = productRepository.findById(productId).orElseThrow(()-> new ResourceNotFoundException("Product with given with Id: " + productId + " not found:"));
        log.info("Product with id " + product.getProductId() + " detail fetched !!");
        ProductDto detailedProduct = mapper.convertValue(product,ProductDto.class);
        return detailedProduct;
    }

    /**
     * Add a new product to the database.
     * @param productDto, Add this Product {@link ProductDto}
     * @return The newly added Product {@link ProductDto}
     */
    @Override
    public ProductDto addProduct(ProductDto productDto) {
        Optional<Product> existingProduct = productRepository.findByProductName(productDto.getProductName());
        if(existingProduct.isPresent()){
            throw new ResourceAlreadyExistsException("Product Already Exists");
        }
        Product product =  mapper.convertValue(productDto,Product.class);
        productRepository.save(product);
        log.info("Product with id " + product.getProductId() + " added successfully!!");
        return mapper.convertValue(product,ProductDto.class);
    }

    /**
     * Update a Product from product value and given id to the database.
     * @param productId, Updated Product's productId {@link Long}
     * @param productDto, Update with this product {@link ProductDto}
     * @return, updated Product {@link ProductDto}
     */
    @Override
    public ProductDto updateProduct(ProductDto productDto, long productId) {
        Product product = productRepository.findById(productId).orElseThrow(()-> new ResourceNotFoundException("Product with given with Id: " + productId + " not found:"));
        product.setProductName(productDto.getProductName());
        product.setProductPrice(productDto.getProductPrice());
        product.setProductDesc(productDto.getProductDesc());
        product.setProductUrl(productDto.getProductUrl());
        product.setCategory(productDto.getCategory());
        productRepository.save(product);
        log.info("Product with productId  " + product.getProductId() +  " updated successfully!!");
        return mapper.convertValue(product,ProductDto.class);
    }

    /**
     * Delete a product from the database with given id.
     * @param productId, Deleted product's productId {@link Long}
     */
    @Override
    public void deleteProduct(long productId) {
        Product product = productRepository.findById(productId).orElseThrow(()-> new ResourceNotFoundException("Product with given with Id: " + productId + " not found:"));
        log.info("Product with productId " + product.getProductId() + " is deleted successfully!!");
        productRepository.delete(product);
    }
}
