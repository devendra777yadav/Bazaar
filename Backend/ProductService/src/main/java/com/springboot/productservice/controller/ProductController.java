package com.springboot.productservice.controller;

import com.springboot.productservice.payload.ProductDto;
import com.springboot.productservice.services.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/product-service/products")
@CrossOrigin("http://localhost:3000")
public class ProductController {
    @Autowired
    private ProductService productService;

    /**
     * Get All Products from the database.
     * @return List of fetched products {@link List<ProductDto>}
     */
    @GetMapping
    public List<ProductDto> getAllProducts(){
        log.info("Fetching all products");
        return productService.getAllProducts();
    }

    /**
     * Get a particular product with productID from the database.
     * @param productId, productId of the detailed product {@link Long}
     * @return Detail of the product {@link ResponseEntity<ProductDto>}
     */
    @GetMapping("/getProductDetail/{productId}")
    public ResponseEntity<ProductDto> getProductDetail(@PathVariable long productId){
        log.info("Fetching the product detail with productID " + productId + " user want"); //unique indentification
        return new ResponseEntity<>(productService.getProductDetail(productId),HttpStatus.OK);
    }

    /**
     * Add a new product to the database.
     * @param productDto, fetching a new product {@link ProductDto}
     * @return newly added product with Http Status {@link ResponseEntity<ProductDto>}
     */
    @PostMapping("/add")
    public ResponseEntity<ProductDto> addProduct(@RequestBody ProductDto productDto){
        log.info("API for adding Product called " + "Product Name : " + productDto.getProductName());
        return new ResponseEntity<>(productService.addProduct(productDto),HttpStatus.CREATED);
    }

    /**
     * Update a Product from product value and given id to the database.
     * @param productId, Update the product with this productId {@link Long}
     * @param productDto, Update with this product {@link ProductDto}
     * @return Updated Product with Http Status {@link ResponseEntity<ProductDto>}
     */
    @PutMapping("/update/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto,@PathVariable long productId){
        log.info("API for updating product called!!" + " Product Id : " + productId);
        return new ResponseEntity<>(productService.updateProduct(productDto,productId),HttpStatus.OK);
    }

    /**
     *
     * @param productId, Delete the product with this productId {@link Long}
     * @return A message  with Http Status {@link String}
     */
    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable long productId){
        productService.deleteProduct(productId);
        log.info("API for deleting product called" + " Product Id : " + productId);
        return new ResponseEntity<>("Product deleted Successfully",HttpStatus.OK);
    }
}
