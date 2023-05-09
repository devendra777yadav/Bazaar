package com.example.cartservice.service.impl;

import com.example.cartservice.entity.Cart;
import com.example.cartservice.entity.Product;
import com.example.cartservice.entity.User;
import com.example.cartservice.exception.ResourceNotFoundException;
import com.example.cartservice.payload.AddToCartDto;
import com.example.cartservice.payload.CartDto;
import com.example.cartservice.payload.CartItemDto;
import com.example.cartservice.repository.CartRepository;
import com.example.cartservice.service.CartService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private RestTemplate restTemplate;

    @Value("${productService.base.url}")
    private String productBaseURL;

    @Override
    public void addToCart(AddToCartDto addToCartDto, Product product, User user) {
        Cart cart = new Cart();
        cart.setProduct(product);
        cart.setQuantity(addToCartDto.getQuantity());
        cart.setUserId(user.getUserId());
        cart.setCreatedDate(new Date());
        cart.setProductId(product.getProductId());
        cartRepository.save(cart);
    }

    @Override
    public CartDto listCartItems(User user) {
        List<Cart> cartList = cartRepository.findAllByUserIdOrderByCreatedDateDesc(user.getUserId());
        if(cartList.size()==0){
            throw new ResourceNotFoundException("Cart is Empty");
        }
        List<CartItemDto> cartItems = new ArrayList<>();
        for (Cart cart:cartList){
            CartItemDto cartItemDto = mapper.convertValue(cart, CartItemDto.class);
            Product product = restTemplate.getForObject(productBaseURL + "getProductDetail/" + cart.getProductId(),Product.class);
            cartItemDto.setProduct(product);
            cartItems.add(cartItemDto);
        }
        double totalCost = 0;
        for (CartItemDto cartItemDto :cartItems){
            totalCost += (cartItemDto.getProduct().getProductPrice()*cartItemDto.getQuantity());
        }
        return new CartDto(cartItems,totalCost);
    }

    @Override
    public void UpdateCartItem(AddToCartDto cartDto, User user, Product product) {
        Cart cart = cartRepository.getReferenceById(cartDto.getCartItemId());
        cart.setQuantity(cartDto.getQuantity());
        cart.setCreatedDate(new Date());
        cartRepository.save(cart);
    }

    @Override
    public void deleteCartItem(long cartItemId, long userId) {
        if (!cartRepository.existsById(cartItemId)) throw new ResourceNotFoundException("Cart id is invalid : " + cartItemId);
        cartRepository.deleteById(cartItemId);
    }

    @Override
    public void deleteUserCartItems(User user) {
        cartRepository.deleteByUserId(user.getUserId());
    }

}
