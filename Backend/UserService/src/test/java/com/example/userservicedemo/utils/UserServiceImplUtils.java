//package com.example.userservicedemo.utils;
//
//import com.example.userservicedemo.entity.User;
//import com.example.userservicedemo.entity.UserAddress;
//import com.example.userservicedemo.payload.LoginDto;
//import com.example.userservicedemo.payload.UserDto;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//
//public class UserServiceImplUtils {
//
//    public static UserAddress ADDRESS_1 = new UserAddress(1,"Ayodhya");
//    public static UserAddress ADDRESS_2 = new UserAddress(2,"Delhi");
//    public static List<UserAddress> ADDRESS_LIST_1 = Arrays.asList(ADDRESS_1,ADDRESS_2);
//    public static List<UserAddress> ADDRESS_LIST_2 = Arrays.asList(ADDRESS_2);
//    public static User USER_1 = new User(1,"Dev","Dev01","Dev01@gmail.com","Dev01",ADDRESS_LIST_1);
//    public static User USER_2 = new User(2,"Mohit","Mohit01","Mohit01@gmail.com","Mohit01",ADDRESS_LIST_2);
//
//    public static UserDto getUserDto(){
//        UserDto userDto =  new UserDto();
//        userDto.setId(3);
//        userDto.setName("Rohan");
//        userDto.setEmail("Rohan01@gmail.com");
//        userDto.setUsername("Rohan01");
//        userDto.setPassword("Rohan01");
//        userDto.setUserAddressList(ADDRESS_LIST_1);
//        return userDto;
//    }
//
//    public static List<User> getUserList(){
//        List<User> users = new ArrayList<>(Arrays.asList(USER_1,USER_2));
//        return users;
//    }
//
//     public static LoginDto getLoginDto(){
//        LoginDto loginDto = new LoginDto();
//        loginDto.setUsername("Dev01");
//        loginDto.setPassword("Dev01");
//        return loginDto;
//     }
//
//     public static List<UserDto> getUserDtoList(){
//         UserDto userDto1 =  new UserDto();
//         userDto1.setId(1);
//         userDto1.setName("Dev");
//         userDto1.setEmail("Dev01@gmail.com");
//         userDto1.setUsername("Dev01");
//         userDto1.setPassword("Dev01");
//         userDto1.setUserAddressList(ADDRESS_LIST_1);
//         UserDto userDto2 =  new UserDto();
//         userDto2.setId(2);
//         userDto2.setName("Mohit");
//         userDto2.setEmail("Mohit01@gmail.com");
//         userDto2.setUsername("Mohit01");
//         userDto2.setPassword("Mohit01");
//         userDto2.setUserAddressList(ADDRESS_LIST_2);
//         return new ArrayList<>(Arrays.asList(userDto1,userDto2));
//     }
//
//}
