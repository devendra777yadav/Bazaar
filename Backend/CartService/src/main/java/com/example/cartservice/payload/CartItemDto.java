package com.example.cartservice.payload;

import com.example.cartservice.entity.Product;
import lombok.Data;

@Data
public class CartItemDto {
    private long cartItemId;
    private  long quantity;
    private Product product;
}
