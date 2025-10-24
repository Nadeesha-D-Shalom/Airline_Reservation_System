package com.example.airlineReservationApp.service;

import com.example.airlineReservationApp.dto.AuthResponse;
import com.example.airlineReservationApp.dto.RegisterRequest;
import com.example.airlineReservationApp.model.Account;
import com.example.airlineReservationApp.model.UserEntity;
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

    // Updated Register method
    public Account register(RegisterRequest req) {
        if (accountRepo.findByEmail(req.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered. Please log in.");
        }

        // Safely combine first and last names
        String fullName = ((req.getFirstName() != null ? req.getFirstName() : "") + " " +
                (req.getLastName() != null ? req.getLastName() : "")).trim();

        if (fullName.isBlank()) {
            fullName = "User";
        }

        // Create Account object
        Account account = new Account();
        account.setFullName(fullName);
        account.setEmail(req.getEmail());
        account.setPassword(passwordEncoder.encode(req.getPassword()));

        // Allow Admin registration if role = ADMIN
        if (req.getRole() != null && req.getRole().equalsIgnoreCase("ADMIN")) {
            account.setRole("ADMIN");
        } else {
            account.setRole("USER");
        }

        accountRepo.save(account);

        // Only create a UserEntity record for normal users
        if (account.getRole().equalsIgnoreCase("USER")) {
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
        }

        return account;
    }


    public AuthResponse login(String email, String password) {
        Optional<Account> userOpt = accountRepo.findByEmail(email);
        if (userOpt.isEmpty()) {
            throw new RuntimeException("User not found with email: " + email);
        }

        Account account = userOpt.get();

        if (!passwordEncoder.matches(password, account.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtService.generateToken(account);
        return new AuthResponse(token, account.getRole());
    }
}
