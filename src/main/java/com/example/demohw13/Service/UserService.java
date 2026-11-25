package com.example.demohw13.Service;

import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.example.demohw13.Model.User;
import com.example.demohw13.Dto.UserRequest;
import com.example.demohw13.Dto.UserResponse;
import com.example.demohw13.Repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponse registerUser(UserRequest request) {

        // Check email already exists
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ResponseStatusException(
                HttpStatus.CONFLICT,
                "Email already registered"
            );
        }

        // Create user object
        User user = new User();

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setMobileNumber(request.getMobileNumber());
        user.setEmail(request.getEmail());

        // Encode password
        user.setPassword(
            passwordEncoder.encode(request.getPassword())
        );

        // Save user
        User savedUser = userRepository.save(user);

        // Create username
        String username = savedUser.getFirstName() + " "
                        + savedUser.getLastName();

        // Return response
        return new UserResponse(
            username,
            savedUser.getEmail()
        );
    }
}
