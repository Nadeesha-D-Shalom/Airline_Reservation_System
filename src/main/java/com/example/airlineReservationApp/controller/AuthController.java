package com.example.airlineReservationApp.controller;

import com.example.airlineReservationApp.dto.AuthRequest;
import com.example.airlineReservationApp.dto.AuthResponse;
import com.example.airlineReservationApp.dto.RegisterRequest;
import com.example.airlineReservationApp.model.Account;
import com.example.airlineReservationApp.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    private final AuthService authService;
    public AuthController(AuthService authService) { this.authService = authService; }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try {
            Account account = authService.register(request);
            return ResponseEntity.ok(account);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", e.getMessage()));
        }
    }


    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        return authService.login(request.getEmail(), request.getPassword());
    }
}
