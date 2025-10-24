package com.example.airlineReservationApp.controller;

import com.example.airlineReservationApp.model.Account;
import com.example.airlineReservationApp.model.AdminEntity;
import com.example.airlineReservationApp.model.UserEntity;
import com.example.airlineReservationApp.model.LoyaltyPoints;
import com.example.airlineReservationApp.repository.AccountRepository;
import com.example.airlineReservationApp.repository.AdminRepository;
import com.example.airlineReservationApp.repository.UserRepository;
import com.example.airlineReservationApp.repository.LoyaltyPointsRepository;
import com.example.airlineReservationApp.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LoyaltyPointsRepository loyaltyPointsRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserEntity newUser) {
        if (newUser.getEmail() == null || newUser.getEmail().isBlank()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Email cannot be empty"));
        }
        if (accountRepository.findByEmail(newUser.getEmail()).isPresent()) {
            return ResponseEntity.status(400).body(Map.of("error", "Email already exists"));
        }

        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        newUser.setRole("USER");

        String fullName = newUser.getFullName();
        if (fullName == null || fullName.isBlank()) {
            fullName = ((newUser.getFirstName() != null ? newUser.getFirstName() : "") + " "
                    + (newUser.getLastName() != null ? newUser.getLastName() : "")).trim();
        }
        newUser.setFullName(fullName);

        Account account = new Account(fullName, newUser.getEmail(), newUser.getPassword(), "USER");
        accountRepository.save(account);
        userRepository.save(newUser);

        //  Automatically create LoyaltyPoints record for new user
        if (!loyaltyPointsRepository.findByEmail(newUser.getEmail()).isPresent()) {
            LoyaltyPoints loyaltyPoints = new LoyaltyPoints(newUser.getEmail());
            loyaltyPointsRepository.save(loyaltyPoints);
        }

        return ResponseEntity.ok(Map.of("message", "Registration successful"));
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String password = request.get("password");

        Account account = accountRepository.findByEmail(email).orElse(null);
        if (account != null && passwordEncoder.matches(password, account.getPassword())) {
            String token = jwtService.generateToken(account);
            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("email", account.getEmail());
            response.put("role", account.getRole());
            return ResponseEntity.ok(response);
        }

        AdminEntity admin = adminRepository.findByEmail(email).orElse(null);
        if (admin != null && passwordEncoder.matches(password, admin.getPassword())) {
            String token = jwtService.generateToken(
                    new Account(admin.getName(), admin.getEmail(), admin.getPassword(), "ADMIN")
            );
            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("email", admin.getEmail());
            response.put("role", "ADMIN");
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(401).body("Invalid email or password");
    }
}
