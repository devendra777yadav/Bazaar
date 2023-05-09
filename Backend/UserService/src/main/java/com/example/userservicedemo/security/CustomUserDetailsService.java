package com.example.userservicedemo.security;

import com.example.userservicedemo.entity.User;
import com.example.userservicedemo.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =  userRepository.findByUsername(username).orElseThrow(()->
                new UsernameNotFoundException("User not found with username"));
        Set<GrantedAuthority> authorities = user.getRoles().stream().map((role -> new SimpleGrantedAuthority(role.getName()))).collect(Collectors.toSet());
        UserDetailsImpl userDetails = new UserDetailsImpl();
        userDetails.setUser(user);
        userDetails.setAuthorities(authorities);
        return userDetails;
    }
}
