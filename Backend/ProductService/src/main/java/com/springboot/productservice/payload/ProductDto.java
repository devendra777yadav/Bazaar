package com.springboot.productservice.payload;

import lombok.Data;

@Data
public class ProductDto {
    private long productId;
    private String productName;
    private String category;
    private String productUrl;
    private String productDesc;
    private double productPrice;
}
