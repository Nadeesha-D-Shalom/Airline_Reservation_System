package com.example.airlineReservationApp.controller;

import com.example.airlineReservationApp.dto.AuthRequest;
import com.example.airlineReservationApp.dto.AuthResponse;
<<<<<<< HEAD
import com.example.airlineReservationApp.dto.RegisterRequest;
import com.example.airlineReservationApp.model.Account;
import com.example.airlineReservationApp.service.AuthService;
=======
import com.example.airlineReservationApp.model.UserEntity;
import com.example.airlineReservationApp.service.AuthService;
import com.example.airlineReservationApp.service.JwtService;
>>>>>>> 4a26394 (Version 1.8.1)
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
<<<<<<< HEAD
@CrossOrigin(origins = "http://localhost:3000")
=======
@CrossOrigin(origins = "*")
>>>>>>> 4a26394 (Version 1.8.1)
public class AuthController {

    private final AuthService authService;
    private final JwtService jwtService;

    public AuthController(AuthService authService, JwtService jwtService) {
        this.authService = authService;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
<<<<<<< HEAD
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try {
            Account account = authService.register(request);
            return ResponseEntity.ok(account);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", e.getMessage()));
        }
=======
    public ResponseEntity<?> register(@RequestBody UserEntity user) {
        if (authService.emailExists(user.getEmail())) {
            return ResponseEntity.badRequest().body("{\"error\":\"Email already exists\"}");
        }

        authService.register(user);
        return ResponseEntity.ok("{\"message\":\"Registration successful\"}");
>>>>>>> 4a26394 (Version 1.8.1)
    }


    @PostMapping("/login")
<<<<<<< HEAD
    public AuthResponse login(@RequestBody AuthRequest request) {
        return authService.login(request.getEmail(), request.getPassword());
=======
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        String token = authService.login(request.getEmail(), request.getPassword());
        String role = jwtService.extractRole(token);
        return ResponseEntity.ok(new AuthResponse(token, role));
>>>>>>> 4a26394 (Version 1.8.1)
    }
}
