package com.example.cartservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cartItemId;

    @Column(name = "created_date")
    private Date createdDate;

    @Transient
    private Product product;

    @Column(name = "quantity")
    private long quantity;

    @Column(name = "product_id")
    private long productId;

    @Column(name = "user_Id")
    private long userId;
}
