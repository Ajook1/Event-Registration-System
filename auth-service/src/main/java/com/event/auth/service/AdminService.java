package com.event.auth.service;

import com.event.auth.dto.RegisterRequestDTO;
import com.event.auth.entity.Role;
import com.event.auth.entity.User;
import com.event.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserHelperService userHelperService;

    // 🔥 CREATE ORGANIZER
    public User createOrganizer(RegisterRequestDTO request) {
        return createUserWithRole(request, Role.ORGANIZER);
    }

    // 🔥 CREATE REGISTRANT
    public User createRegistrant(RegisterRequestDTO request) {
        return createUserWithRole(request, Role.REGISTRANT);
    }

    // 🔥 COMMON METHOD
    private User createUserWithRole(RegisterRequestDTO request, Role role) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered");
        }

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(role);

        User savedUser = userRepository.save(user);

        userHelperService.saveUserDetails(savedUser, request);

        return savedUser;
    }
}