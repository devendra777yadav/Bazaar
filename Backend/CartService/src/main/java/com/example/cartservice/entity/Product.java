package com.example.cartservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * product class with all
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private long productId;
    private String productName;
    private String productUrl;
    private String productDesc;
    private int productPrice;
}
