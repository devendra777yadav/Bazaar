package com.example.userservicedemo.payload;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JWTAuthResponse {
    private String accessToken;
    private String tokenType;
    private String refreshToken;
    private UserDto userDto;
}
