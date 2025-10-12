package com.example.airlineReservationApp.service;

import com.example.airlineReservationApp.dto.AuthResponse;
import com.example.airlineReservationApp.dto.RegisterRequest;
import com.example.airlineReservationApp.model.Account;
import com.example.airlineReservationApp.model.UserEntity;
<<<<<<< HEAD
import com.example.airlineReservationApp.repository.AccountRepository;
import com.example.airlineReservationApp.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final AccountRepository accountRepo;
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(AccountRepository accountRepo,
                       UserRepository userRepo,
                       PasswordEncoder passwordEncoder,
                       JwtService jwtService) {
        this.accountRepo = accountRepo;
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public Account register(RegisterRequest req) {
        if (accountRepo.findByEmail(req.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered. Please log in.");
        }

        // Safely combine first + last name
        String fullName = ((req.getFirstName() != null ? req.getFirstName() : "") + " " +
                (req.getLastName() != null ? req.getLastName() : "")).trim();

        if (fullName.isBlank()) fullName = "User";

        // Save to Account table
        Account account = new Account();
        account.setFullName(fullName);
        account.setEmail(req.getEmail());
        account.setPassword(passwordEncoder.encode(req.getPassword()));
        account.setRole("USER");
        accountRepo.save(account);

        // Save to UserEntity table
        UserEntity user = new UserEntity();
        user.setFullName(fullName);
        user.setFirstName(req.getFirstName());
        user.setLastName(req.getLastName());
        user.setEmail(req.getEmail());
        user.setPassword(account.getPassword());
        user.setRole(account.getRole());
        user.setAddress(req.getAddress());
        user.setPhoneNumber(req.getPhoneNumber());
        userRepo.save(user);

        return account;
    }


    // Login method (for authentication)
    public AuthResponse login(String email, String password) {
        Optional<Account> userOpt = accountRepo.findByEmail(email);
        if (userOpt.isEmpty()) {
            throw new RuntimeException("User not found with email: " + email);
        }

        Account account = userOpt.get();

        if (!passwordEncoder.matches(password, account.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        //  Generate JWT token
        String token = jwtService.generateToken(account);

        // Return token + role
        return new AuthResponse(token, account.getRole());
    }
=======
import com.example.airlineReservationApp.dto.AuthRequest;
import com.example.airlineReservationApp.dto.AuthResponse;

public interface AuthService {
    boolean emailExists(String email);
    Account register(Account account);
    String login(String email, String password);
>>>>>>> 4a26394 (Version 1.8.1)
}
