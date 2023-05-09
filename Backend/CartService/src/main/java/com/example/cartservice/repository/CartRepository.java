package com.example.cartservice.repository;

import com.example.cartservice.entity.Cart;
import com.example.cartservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
    List<Cart> findAllByUserIdOrderByCreatedDateDesc(long userId);
    List<Cart> deleteByUserId(long userId);
}
