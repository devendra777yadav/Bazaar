package com.example.userservicedemo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "userAddress")

public class UserAddress {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long addressId;

    @NotEmpty(message = "address cannot be empty")
    @Column(name="address")
    private String address;
}
