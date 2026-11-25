package com.example.demohw13.Controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

import com.example.demohw13.Dto.UserRequest;
import com.example.demohw13.Dto.UserResponse;
import com.example.demohw13.Service.UserService;

@RestController
@RequestMapping("/api/users")
@Validated
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(
            @Valid @RequestBody UserRequest request) {

        UserResponse response =
                userService.registerUser(request);

        return new ResponseEntity<>(
            response,
            HttpStatus.CREATED
        );
    }
}
