package com.springboot.productservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * product class with all
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products",uniqueConstraints = {@UniqueConstraint(columnNames = {"product_name"})})
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productId;
    @Column(name="product_name", nullable = false)
    private String productName;
    @Column(name = "category")
    private String category;
    @Column(name = "product_url")
    private String productUrl;
    @Column(name="product_desc")
    private String productDesc;
    @Column(name="productPrice")
    private double productPrice;
}
