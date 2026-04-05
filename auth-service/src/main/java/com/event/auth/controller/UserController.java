package com.event.auth.controller;

import com.event.auth.dto.ProfileResponseDTO;
import com.event.auth.dto.RegisterRequestDTO;
import com.event.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public ProfileResponseDTO getProfile(@RequestParam String email) {
        return userService.getProfile(email);
    }

    @PutMapping("/profile")
    public ProfileResponseDTO updateProfile(
            @RequestParam String email,
            @RequestBody RegisterRequestDTO request) {

        return userService.updateProfile(email, request);
    }
}