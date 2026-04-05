package com.event.auth.dto;

import lombok.Data;

@Data
public class ProfileResponseDTO {

    private String name;
    private String email;
    private String role;

    private String address;
    private String mobile;
    private String city;
    private String state;
    private String pincode;
}