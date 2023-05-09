package com.springboot.productservice.services;

import com.springboot.productservice.payload.ProductDto;

import java.util.List;

public interface ProductService {
    List<ProductDto> getAllProducts();
    ProductDto getProductDetail(long id);
    ProductDto addProduct(ProductDto productDto);
    ProductDto updateProduct(ProductDto productDto,long id);
    void deleteProduct(long id);
}
