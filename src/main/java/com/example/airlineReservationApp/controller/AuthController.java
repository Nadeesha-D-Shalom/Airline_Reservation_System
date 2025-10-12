package com.example.airlineReservationApp.controller;

import com.example.airlineReservationApp.dto.AuthRequest;
import com.example.airlineReservationApp.dto.AuthResponse;
import com.example.airlineReservationApp.model.UserEntity;
import com.example.airlineReservationApp.service.AuthService;
import com.example.airlineReservationApp.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;
    private final JwtService jwtService;

    public AuthController(AuthService authService, JwtService jwtService) {
        this.authService = authService;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserEntity user) {
        if (authService.emailExists(user.getEmail())) {
            return ResponseEntity.badRequest().body("{\"error\":\"Email already exists\"}");
        }

        authService.register(user);
        return ResponseEntity.ok("{\"message\":\"Registration successful\"}");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        String token = authService.login(request.getEmail(), request.getPassword());
        String role = jwtService.extractRole(token);
        return ResponseEntity.ok(new AuthResponse(token, role));
    }
}
