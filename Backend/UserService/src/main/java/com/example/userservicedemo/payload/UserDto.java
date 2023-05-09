package com.example.userservicedemo.payload;
import com.example.userservicedemo.entity.UserAddress;
import lombok.Data;

import java.util.List;

/**
 *  Class for UserDto
 */
@Data
public class UserDto {
    private long userId;
    private String username;
}
