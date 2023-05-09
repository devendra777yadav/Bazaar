package com.example.userservicedemo.controller;
import com.example.userservicedemo.entity.User;
import com.example.userservicedemo.payload.*;
import com.example.userservicedemo.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;


/**
 *  Public class of user controller
 */
@RestController
@Log4j2
@RequestMapping("/users")
@CrossOrigin("http://localhost:3000")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * controller for register new user
     * @param {@link userDto}
     * @return ResponseEntity which includes userDto of new user and http status
     */
    @PostMapping("/auth/register")
    public ResponseEntity<UserDto> registerUser(@Valid @RequestBody User user){
        log.info("Register User controller executing");
        return new ResponseEntity<>(userService.registerUser(user), HttpStatus.CREATED);
    }
    @PostMapping("/auth/login")
    public ResponseEntity<JWTAuthResponse> login(@Valid @RequestBody LoginDto loginDto){
        JWTAuthResponse jwtAuthResponse = userService.login(loginDto);
        return  new ResponseEntity<>(jwtAuthResponse,HttpStatus.OK);
    }

    /**
     * @return List of all users {@link List<UserDto>}
     */
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<UserDto> getAllUsers(){
        log.info("Getting all the user");
        return userService.getAllUsers();
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("userId") long userId){
        log.info("Getting user with id {}", userId);
        return new ResponseEntity<>(userService.getUserById(userId),HttpStatus.OK);
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<TokenRefreshResponse> refreshToken(@RequestBody TokenRefreshRequest request) {
        return new ResponseEntity<>(userService.refreshToken(request),HttpStatus.OK);
    }

    @PostMapping("/logout/{userId}")
    public ResponseEntity<String> logoutUser(@PathVariable("userId") long userId) {
        userService.logoutUser(userId);
        return new ResponseEntity<>("Log out successful!",HttpStatus.OK);
    }

}
