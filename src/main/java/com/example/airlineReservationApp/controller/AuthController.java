package com.example.airlineReservationApp.controller;

import com.example.airlineReservationApp.dto.AuthRequest;
import com.example.airlineReservationApp.dto.AuthResponse;
import com.example.airlineReservationApp.model.UserEntity;
import com.example.airlineReservationApp.model.Account;
import com.example.airlineReservationApp.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    //  Register endpoint for normal users
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserEntity user) {
        if (authService.emailExists(user.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body("{\"error\":\"Email already exists\"}");
        }

        Account registered = authService.register(user);
        return ResponseEntity.ok("{\"message\":\"Registration successful for " + registered.getEmail() + "\"}");
    }

    //  Login endpoint for both user and admin
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        String token = authService.login(request.getEmail(), request.getPassword());
        AuthResponse response = new AuthResponse(token, "USER_OR_ADMIN");
        return ResponseEntity.ok(response);
    }
}
