package com.pm.authservice.controller;

import com.pm.authservice.dto.LoginRequestDTO;
import com.pm.authservice.dto.LoginResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class AuthController {
    @Operation(summary = "Generate token on user login")
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(
            @RequestBody LoginRequestDTO loginRequestDTO) {

        Optional<String> tokenOptional=authService.authenticate(loginRequestDTO);

        if(tokenOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String token=tokenOptional.get();
        return ResponseEntity.ok(new LoginResponseDTO(token));

    }



}