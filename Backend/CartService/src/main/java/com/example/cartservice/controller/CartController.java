package com.example.cartservice.controller;


import com.example.cartservice.entity.Cart;
import com.example.cartservice.entity.Product;
import com.example.cartservice.entity.User;
import com.example.cartservice.exception.ResourceNotFoundException;
import com.example.cartservice.payload.AddToCartDto;
import com.example.cartservice.payload.CartDto;
import com.example.cartservice.repository.CartRepository;
import com.example.cartservice.service.CartService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@Transactional
@RequestMapping("/cart-service")
@CrossOrigin("http://localhost:3000")
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ObjectMapper mapper;
    @Value("${productService.base.url}")
    private String productBaseURL;
    @Value("${userService.base.url}")
    private String userBaseURL;

    @PostMapping("/add")
    public ResponseEntity<String> addToCart(@RequestBody AddToCartDto addToCartDto, @RequestParam("productId") long productId, @RequestParam("userId") long userId){
        Product product = restTemplate.getForObject(productBaseURL + "getProductDetail/" + productId,Product.class);
        User user = restTemplate.getForObject(userBaseURL + userId,User.class);
        cartService.addToCart(addToCartDto, product, user);
        return new ResponseEntity<>("Product added to Cart",HttpStatus.CREATED);
    }

    @GetMapping("/getCartItems")
    public ResponseEntity<CartDto> getCartItems(@RequestParam("userId") long userId){
        User user = restTemplate.getForObject(userBaseURL + userId,User.class);
        CartDto cartDto = cartService.listCartItems(user);
        return new ResponseEntity<>(cartDto,HttpStatus.OK);
    }

    @PutMapping("/update/{cartItemId}")
    public ResponseEntity<String> updateCartItem(@RequestBody AddToCartDto cartDto, @PathVariable("cartItemId") long cartItemId,@RequestParam("userId") long userId){
        Cart cart = cartRepository.findById(cartItemId).orElseThrow(()->new ResourceNotFoundException("cart item not exist"));
        cartDto.setProductId(cart.getProductId());
        cartDto.setCartItemId(cartItemId);
        Product product = restTemplate.getForObject(productBaseURL + "getProductDetail/" + cartDto.getProductId(),Product.class);
        User user = restTemplate.getForObject(userBaseURL + userId,User.class);
        cartService.UpdateCartItem(cartDto,user,product);
        return new ResponseEntity<>("Updated Cart Item",HttpStatus.OK);
    }

    @DeleteMapping("/delete/{cartItemId}")
    public ResponseEntity<String> deleteCartItem(@PathVariable("cartItemId") long cartItemId, @RequestParam("userId") long userId){
        cartService.deleteCartItem(cartItemId, userId);
        return new ResponseEntity<>("Item has been removed", HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteCart(@RequestParam("userId") long userId){
        User user = restTemplate.getForObject(userBaseURL + userId,User.class);
        cartService.deleteUserCartItems(user);
        return new ResponseEntity<>("cart has been deleted", HttpStatus.OK);
    }
}
