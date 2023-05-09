package com.example.cartservice.payload;

import lombok.Data;
@Data
public class AddToCartDto {
    private long cartItemId;
    private long productId;
    private long quantity;
}
