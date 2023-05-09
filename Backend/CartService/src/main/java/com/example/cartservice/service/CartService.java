package com.example.cartservice.service;

import com.example.cartservice.entity.Product;
import com.example.cartservice.entity.User;
import com.example.cartservice.payload.AddToCartDto;
import com.example.cartservice.payload.CartDto;
import org.springframework.stereotype.Service;

@Service
public interface CartService {
    void addToCart(AddToCartDto addToCartDto, Product product, User user);
    CartDto listCartItems(User user);
    void UpdateCartItem(AddToCartDto cartDto, User user, Product product);
    void deleteCartItem(long cartId, long userId);
    void deleteUserCartItems(User user);
}
