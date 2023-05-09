//package com.example.userservicedemo.service;
//
//import com.example.userservicedemo.entity.User;
//import com.example.userservicedemo.payload.LoginDto;
//import com.example.userservicedemo.payload.UserDto;
//import com.example.userservicedemo.repository.UserAddressRepository;
//import com.example.userservicedemo.repository.UserRepository;
//import com.example.userservicedemo.utils.UserServiceImplUtils;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Spy;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//
//
//class UserServiceImplTest {
//
//    @InjectMocks
//    private UserServiceImpl userService;
//
//    @Mock
//    private UserRepository userRepository;
//
//    @Mock
//    private UserAddressRepository userAddressRepository;
//
//    @Spy
//    private ObjectMapper mapper;
//    @Test
//    void testRegisterUser() {
//
//        // Given
//        UserDto expected= UserServiceImplUtils.getUserDto();
//        UserDto request = UserServiceImplUtils.getUserDto();
//        // When
//        UserDto actual = userService.registerUser(request);
//        // Then
//        assertThat(actual).isEqualTo(expected);
//    }
//
//
//
//    @Test
//    void testLoginUser() {
//        //Given
//        boolean expected = true;
//
//      // when(userRepository.findAll()).thenReturn(UserServiceImplUtils.getUserList());
//        LoginDto request = UserServiceImplUtils.getLoginDto();
//        when(userRepository.findByUsername(request.getUsername())).thenReturn(Optional.of(UserServiceImplUtils.USER_1));
//        // When
//        boolean actual = userService.loginUser(request);
//        // Then
//        assertThat(actual).isEqualTo(expected);
//    }
//    @Test
//    void testGetAllUsers() {
//        //Given
//        List<UserDto> expected = UserServiceImplUtils.getUserDtoList();
//        List<User> userList = UserServiceImplUtils.getUserList();
//        when(userRepository.findAll()).thenReturn(userList);
//
//        //When
//        List<UserDto> actual = userService.getAllUsers();
//
//        // Then
//        assertThat(actual).isEqualTo(expected);
//    }
//}