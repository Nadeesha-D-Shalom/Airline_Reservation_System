package com.example.airlineReservationApp.service;

import com.example.airlineReservationApp.model.*;
import com.example.airlineReservationApp.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepo;
    private final AdminRepository adminRepo;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;

    public AuthServiceImpl(UserRepository userRepo, AdminRepository adminRepo,
                           PasswordEncoder encoder, JwtService jwtService) {
        this.userRepo = userRepo;
        this.adminRepo = adminRepo;
        this.encoder = encoder;
        this.jwtService = jwtService;
    }

    // ✅ Login logic for both users and admins
    @Override
    @Transactional
    public String login(String email, String password) {
        // Try to find account by email in both tables
        Account account = userRepo.findByEmail(email)
                .map(u -> (Account) u)
                .or(() -> adminRepo.findByEmail(email).map(a -> (Account) a))
                .orElseThrow(() -> new RuntimeException("Account not found"));

        // Validate password
        if (!encoder.matches(password, account.getPassword()))
            throw new RuntimeException("Invalid credentials");

        // Generate JWT token
        return jwtService.generateToken(account);
    }

    @Override
    @Transactional
    public Account register(Account account) {

        // Public user registration — always USER
        if (account instanceof UserEntity) {
            UserEntity user = (UserEntity) account;

            if (user.getRoleEnum() == null) {
                user.setRole(BaseUser.Role.USER); // assign default USER role
            }

            user.setPassword(encoder.encode(user.getPassword()));

            userRepo.insertUser(
                    user.getEmail(),
                    user.getName(),
                    user.getPassword(),
                    user.getRoleEnum().name(),
                    user.getAddress(),
                    user.getPhoneNumber()
            );

            return userRepo.findByEmail(user.getEmail())
                    .orElseThrow(() -> new RuntimeException("User registration failed"));
        }

        // Admin registration — only from admin dashboard
        if (account instanceof AdminEntity) {
            AdminEntity admin = (AdminEntity) account;

            if (admin.getRoleEnum() == null) {
                admin.setRole(BaseUser.Role.ADMIN); // assign default ADMIN role
            }

            admin.setPassword(encoder.encode(admin.getPassword()));

            adminRepo.insertAdmin(
                    admin.getEmail(),
                    admin.getName(),
                    admin.getPassword(),
                    admin.getRoleEnum().name(),
                    admin.getDepartment()
            );

            return adminRepo.findByEmail(admin.getEmail())
                    .orElseThrow(() -> new RuntimeException("Admin registration failed"));
        }

        throw new RuntimeException("Unsupported account type");
    }
}
