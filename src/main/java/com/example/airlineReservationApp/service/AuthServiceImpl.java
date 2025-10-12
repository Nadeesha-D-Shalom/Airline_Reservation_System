package com.example.airlineReservationApp.service;

import com.example.airlineReservationApp.dto.AuthResponse;
import com.example.airlineReservationApp.model.Account;
import com.example.airlineReservationApp.repository.AccountRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthServiceImpl(AccountRepository accountRepository,
                           PasswordEncoder passwordEncoder,
                           JwtService jwtService) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public Account register(Account account) {
        if (accountRepository.existsByEmail(account.getEmail())) {
            throw new RuntimeException("Email already registered");
        }
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        if (account.getRole() == null || account.getRole().isBlank()) {
            account.setRole("USER");
        }
        return accountRepository.save(account);
    }

    @Override
    public AuthResponse login(String email, String password) {
        var user = accountRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        String token = jwtService.generateToken(user);
        return new AuthResponse(token, user.getRole());
    }
}
