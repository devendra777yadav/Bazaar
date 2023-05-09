package com.springboot.productservice.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.productservice.entity.Product;
import com.springboot.productservice.payload.ProductDto;
import com.springboot.productservice.repository.ProductRepository;
import com.springboot.productservice.services.utils.ProductServiceImplUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {
    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private ProductServiceImpl productService;
    @Spy
    private ObjectMapper mapper = new ObjectMapper();
    @Test
    void testGetAllProducts() {
        //given
        List<ProductDto> expected = ProductServiceImplUtils.getProductDtoList();

        List<Product> products = ProductServiceImplUtils.product_list1;
        when(productRepository.findAll()).thenReturn(products);
        //when
        List<ProductDto> actual = productService.getAllProducts();
        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void testGetProductDetail() {
        //given
        long id = 1;
        ProductDto expected = ProductServiceImplUtils.getProductDtoList().get(0);
        when(productRepository.findById(id)).thenReturn(Optional.ofNullable(ProductServiceImplUtils.product1));
        //when
        ProductDto actual = productService.getProductDetail(id);
        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void addProduct() {
        //given
        ProductDto expected = ProductServiceImplUtils.getProductDto();
        //when
        ProductDto actual = productService.addProduct(ProductServiceImplUtils.getProductDto());
        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void updateProduct() {
        ProductDto expected = ProductServiceImplUtils.updateProductDto();
        expected.setProductDesc("Coding + Gaming");
        ProductDto request = ProductServiceImplUtils.updateProductDto();
        request.setProductDesc("Coding + Gaming");
        when(productRepository.findById(request.getProductId())).thenReturn(Optional.of(ProductServiceImplUtils.product3));
        //when
        ProductDto actual = productService.updateProduct(request,request.getProductId());
        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void deleteProduct() {
        //given
        long id = 1;
        when(productRepository.findById(id)).thenReturn(Optional.of(ProductServiceImplUtils.product1));
        doNothing().when(productRepository).delete(ProductServiceImplUtils.product1);
        //when
        productService.deleteProduct(id);
        //then
        verify(productRepository,times(1)).delete(ProductServiceImplUtils.product1);
    }
}