package com.event.auth.dto;

import com.event.auth.entity.Role;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class RegisterRequestDTO {

    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    private String address;
    private String mobile;
    private String city;
    private String state;
    private String pincode;

    @NotNull
    private Role role;
}