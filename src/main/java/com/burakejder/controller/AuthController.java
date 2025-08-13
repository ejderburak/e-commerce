package com.burakejder.controller;

import com.burakejder.DTO.DtoAuthResponse;
import com.burakejder.DTO.DtoUserLogin;
import com.burakejder.DTO.DtoUserRegister;
import com.burakejder.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthController {

    private final UserService userService;

    // /api/v1/auth/register
    @PostMapping("/register")
    public ResponseEntity<DtoAuthResponse> register(@Valid @RequestBody DtoUserRegister registerDto) {
        DtoAuthResponse response = userService.register(registerDto);

        if (response.isSuccess()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    // /api/v1/auth/login
    @PostMapping("/login")
    public ResponseEntity<DtoAuthResponse> login(@Valid @RequestBody DtoUserLogin loginDto) {
        DtoAuthResponse response = userService.login(loginDto);

        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
}