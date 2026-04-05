package com.event.auth.service;

import com.event.auth.dto.LoginRequestDTO;
import com.event.auth.dto.LoginResponseDTO;
import com.event.auth.dto.RegisterRequestDTO;
import com.event.auth.entity.User;
import com.event.auth.exception.ResourceNotFoundException;
import com.event.auth.repository.UserRepository;
import com.event.auth.security.JwtUtil;
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

    @Autowired
    private UserHelperService userHelperService;

    // 🔥 REGISTER
    public User register(RegisterRequestDTO request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered");
        }

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());

        User savedUser = userRepository.save(user);

        userHelperService.saveUserDetails(savedUser, request);

        return savedUser;
    }

    // 🔥 LOGIN
    public LoginResponseDTO login(LoginRequestDTO request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(user);

        return new LoginResponseDTO(token);
    }
}