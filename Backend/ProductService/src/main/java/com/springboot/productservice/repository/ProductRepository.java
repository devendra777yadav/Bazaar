package com.springboot.productservice.repository;

import com.springboot.productservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import com.springboot.productservice.payload.ProductDto;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByProductName(String productName);
}

