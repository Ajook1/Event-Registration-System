package com.event.auth.controller;

import com.event.auth.dto.LoginRequestDTO;
import com.event.auth.dto.LoginResponseDTO;
import com.event.auth.dto.RegisterRequestDTO;
import com.event.auth.entity.User;
import com.event.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


//USED THE SINGLE RESPONSIBILITY PRINCIPAL
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public User register(@RequestBody RegisterRequestDTO request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO request) {
        return authService.login(request);
    }
}