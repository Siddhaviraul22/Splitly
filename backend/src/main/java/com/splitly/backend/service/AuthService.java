package com.splitly.backend.service;

import com.splitly.backend.dto.LoginRequest;
import com.splitly.backend.dto.RegisterRequest;
import com.splitly.backend.entity.User;
import com.splitly.backend.repository.UserRepository;
import com.splitly.backend.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public String register(RegisterRequest request) {

        if (userRepository.findByEmail(request.email) != null) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setName(request.name);
        user.setEmail(request.email);
        user.setPassword(
                passwordEncoder.encode(request.password)
        );

        userRepository.save(user);

        return "User registered successfully";
    }

    public String login(LoginRequest request) {

        User user = userRepository.findByEmail(request.email);

        if (user == null ||
                !passwordEncoder.matches(
                        request.password,
                        user.getPassword()
                )) {
            throw new RuntimeException("Invalid credentials");
        }

        return jwtUtil.generateToken(user.getEmail());
    }
}