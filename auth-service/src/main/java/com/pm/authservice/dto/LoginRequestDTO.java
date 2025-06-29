package com.pm.authservice.dto;

import jakarta.validation.constraints.*;

public class LoginRequestDTO {
    @NotBlank(message="Email is required")
    @Email(message ="Email should be a valid email address")
    private String email;

    @NotBlank(message="Email is required")
    @Size(min=8,message ="Password must be at least 8 characters long")
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}