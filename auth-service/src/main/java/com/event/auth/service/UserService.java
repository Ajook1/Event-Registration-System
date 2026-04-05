package com.event.auth.service;

import com.event.auth.dto.ProfileResponseDTO;
import com.event.auth.dto.RegisterRequestDTO;
import com.event.auth.entity.User;
import com.event.auth.entity.UserDetails;
import com.event.auth.exception.ResourceNotFoundException;
import com.event.auth.repository.UserDetailsRepository;
import com.event.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // 🔥 GET PROFILE
    public ProfileResponseDTO getProfile(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        UserDetails details = userDetailsRepository.findByUserId(user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User details not found"));

        ProfileResponseDTO response = new ProfileResponseDTO();

        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setRole(user.getRole().name());

        response.setAddress(details.getAddress());
        response.setMobile(details.getMobile());
        response.setCity(details.getCity());
        response.setState(details.getState());
        response.setPincode(details.getPincode());

        return response;
    }

    // 🔥 UPDATE PROFILE
    public ProfileResponseDTO updateProfile(String email, RegisterRequestDTO request) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        UserDetails details = userDetailsRepository.findByUserId(user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User details not found"));

        user.setName(request.getName());

        if (request.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        details.setAddress(request.getAddress());
        details.setMobile(request.getMobile());
        details.setCity(request.getCity());
        details.setState(request.getState());
        details.setPincode(request.getPincode());

        userRepository.save(user);
        userDetailsRepository.save(details);

        return getProfile(email);
    }
}