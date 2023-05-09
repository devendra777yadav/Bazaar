package com.example.userservicedemo.service;

import com.example.userservicedemo.entity.RefreshToken;
import com.example.userservicedemo.entity.Role;
import com.example.userservicedemo.entity.User;
import com.example.userservicedemo.entity.UserAddress;
import com.example.userservicedemo.exception.TokenRefreshException;
import com.example.userservicedemo.exception.UserExistException;
import com.example.userservicedemo.exception.UserNotExistException;
import com.example.userservicedemo.payload.*;
import com.example.userservicedemo.repository.RoleRepository;
import com.example.userservicedemo.repository.UserAddressRepository;
import com.example.userservicedemo.repository.UserRepository;
import com.example.userservicedemo.security.JwtTokenProvider;
import com.example.userservicedemo.security.UserDetailsImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 *  UserServiceImpl class which implements UserService
 */
@Log4j2
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserAddressRepository userAddressRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RefreshTokenService refreshTokenService;
    @Autowired
    private ObjectMapper mapper;


    /**
     * @param user
     * @return UserDto {@link UserDto}
     */
    @Override
    public UserDto registerUser(User user) {
        if(userRepository.findByUsername(user.getUsername()).isPresent()){
            log.info("User Already Registered");
            throw new UserExistException("User with username or email already exists");
        }
        List<UserAddress> userAddressList = user.getUserAddressList();
        user.setUserAddressList(userAddressList);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER").get();
        roles.add(userRole);
        user.setRoles(roles);
        userAddressRepository.saveAll(userAddressList);
        userRepository.save(user);
        UserDto newAddedUser = mapper.convertValue(user,UserDto.class);
        log.info("User Successfully registered");
        return newAddedUser;
    }


    /**
     * @return List of all user with their UserDto{@link List<UserDto>}
     */
    @Override
    public List<UserDto> getAllUsers(){
        List<User> users = userRepository.findAll();
        log.info("All the users are accessible");
        return users.stream().map(user->mapper.convertValue(user,UserDto.class
        )).collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(long userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new UserNotExistException("User with id " + userId +" does not exist"));
        return mapper.convertValue(user, UserDto.class);
    }

    @Override
    public JWTAuthResponse login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(),loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String token = jwtTokenProvider.generateToken(authentication);
        String refreshToken = refreshTokenService.createRefreshToken(userDetails.getUserId()).getToken();
        UserDto userDto = mapper.convertValue(userDetails, UserDto.class);
        JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
        jwtAuthResponse.setAccessToken(token);
        jwtAuthResponse.setTokenType("Bearer");
        jwtAuthResponse.setRefreshToken(refreshToken);
        jwtAuthResponse.setUserDto(userDto);
        return jwtAuthResponse;
    }

    @Override
    public TokenRefreshResponse refreshToken(TokenRefreshRequest request) {
        String requestRefreshToken = request.getRefreshToken();
        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String token = jwtTokenProvider.generateTokenFromUsername(user.getUsername());
                    return new TokenRefreshResponse(token, requestRefreshToken,"Bearer");
                })
                .orElseThrow(() -> new TokenRefreshException("Refresh token is not in database!"));
    }

    @Override
    public void logoutUser(long userId) {
//        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        refreshTokenService.deleteByUserId(userId);
    }

}