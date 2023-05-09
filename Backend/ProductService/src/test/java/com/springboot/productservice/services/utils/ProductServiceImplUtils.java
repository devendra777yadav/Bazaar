package com.springboot.productservice.services.utils;

import com.springboot.productservice.entity.Product;
import com.springboot.productservice.payload.ProductDto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductServiceImplUtils {
    public static Product product1 = new Product(1,"Bag","Store",200);
    public static Product product2 = new Product(2,"Pencil","Writing",5);
    public static Product product3 = new Product(4,"laptop","Coding",45000);
    public static List<Product> product_list1 = new ArrayList<>(Arrays.asList(product1,product2));
    public static ProductDto productDto1 = new ProductDto();
    public static ProductDto productDto2 = new ProductDto();
    public static ProductDto productDto3 = new ProductDto();

    //public static List<ProductDto> productDtoList = new ArrayList<>();
    public static List<ProductDto> getProductDtoList(){
        productDto1.setProductId(1);
        productDto1.setProductName("Bag");
        productDto1.setProductDesc("Store");
        productDto1.setProductPrice(200);

        productDto2.setProductId(2);
        productDto2.setProductName("Pencil");
        productDto2.setProductDesc("Writing");
        productDto2.setProductPrice(5);

//        productDtoList.add(productDto1);
//        productDtoList.add(productDto2);
//        return productDtoList;
        return new ArrayList<>(Arrays.asList(productDto1,productDto2));
    }

    public static ProductDto getProductDto(){
        ProductDto productDto3 = new ProductDto();
        productDto3.setProductId(3);
        productDto3.setProductName("Pen");
        productDto3.setProductDesc("Writing");
        productDto3.setProductPrice(10);

        //List<Product> productDtoList =

        return productDto3;
    }

    public static ProductDto updateProductDto(){
        productDto3.setProductId(4);
        productDto3.setProductName("laptop");
        productDto3.setProductDesc("Coding");
        productDto3.setProductPrice(45000);

        return productDto3;
    }

}
