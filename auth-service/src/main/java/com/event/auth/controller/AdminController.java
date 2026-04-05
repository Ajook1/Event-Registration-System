package com.event.auth.controller;

import com.event.auth.dto.RegisterRequestDTO;
import com.event.auth.entity.User;
import com.event.auth.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/organizers")
    public User createOrganizer(@RequestBody RegisterRequestDTO request) {
        return adminService.createOrganizer(request);
    }

    @PostMapping("/registrants")
    public User createRegistrant(@RequestBody RegisterRequestDTO request) {
        return adminService.createRegistrant(request);
    }
}