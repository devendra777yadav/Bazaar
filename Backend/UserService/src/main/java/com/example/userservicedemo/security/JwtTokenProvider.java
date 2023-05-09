package com.example.userservicedemo.security;

import com.example.userservicedemo.exception.UserServiceAPIException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.stereotype.Component;

import java.security.Key;


import java.util.Date;
import java.util.stream.Collectors;


@Component
public class JwtTokenProvider {
//    private JwtEncoder jwtEncoder;
    private String jwtSecret = "a97090370373569707f5c77d360501ced1e02d75ac99ed665fa8891606e94181";
    private long jwtExpirationDate = 3600000;

//     generate JWT token
    public String generateToken(Authentication authentication){
        String username = authentication.getName();
        Date currentDate = new Date();

        Date expireDate = new Date(currentDate.getTime() + jwtExpirationDate);

          return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(key())
                .compact();
    }
//
//    public String generateToken(Authentication authentication){
//        Instant now = Instant.now();
//        String scope = authentication.getAuthorities().stream()
//                .map(GrantedAuthority::getAuthority)
//                .collect(Collectors.joining(" "));
//
//        JwtClaimsSet claims = JwtClaimsSet.builder()
//                .issuer("self")
//                .issuedAt(now)
//                .expiresAt(now.plus(1, ChronoUnit.HOURS))
//                .subject(authentication.getName())
//                .claim("scope",scope)
//                .build();
//        return this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
//    }

//    public String generateJwtToken(UserDetailsImpl userPrincipal) {
//        return generateTokenFromUsername(userPrincipal.getUsername());
//    }

    public String generateTokenFromUsername(String username) {
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + jwtExpirationDate);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(key())
                .compact();
    }

    private Key key(){
        return Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(jwtSecret)
        );
    }

    // get username from Jwt token
    public String getUsername(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    // validate Jwt token
    public boolean validateToken(String token){
        try{
            Jwts.parserBuilder()
                    .setSigningKey(key())
                    .build()
                    .parse(token);
            return true;
        } catch (MalformedJwtException ex) {
            throw new UserServiceAPIException("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            throw new UserServiceAPIException("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            throw new UserServiceAPIException("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            throw new UserServiceAPIException("JWT claims string is empty.");
        }
    }
}
