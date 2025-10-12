package com.example.airlineReservationApp.service;

import com.example.airlineReservationApp.model.*;
import com.example.airlineReservationApp.repository.AdminRepository;
import com.example.airlineReservationApp.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepo;
    private final AdminRepository adminRepo;
    private final JwtService jwtService;
    private final PasswordEncoder encoder = new BCryptPasswordEncoder();

    public AuthServiceImpl(UserRepository userRepo, AdminRepository adminRepo, JwtService jwtService) {
        this.userRepo = userRepo;
        this.adminRepo = adminRepo;
        this.jwtService = jwtService;
    }

    @Override
    public boolean emailExists(String email) {
        return userRepo.findByEmail(email).isPresent() || adminRepo.findByEmail(email).isPresent();
    }

    @Override
    @Transactional
    public Account register(Account account) {
        if (account instanceof UserEntity user) {
            user.setPassword(encoder.encode(user.getPassword()));
            user.setRole(BaseUser.Role.USER);
            return userRepo.save(user);
        }
        if (account instanceof AdminEntity admin) {
            admin.setPassword(encoder.encode(admin.getPassword()));
            admin.setRole(BaseUser.Role.ADMIN);
            return adminRepo.save(admin);
        }
        throw new RuntimeException("Unsupported account type");
    }

    @Override
    public String login(String email, String password) {
        Account account = userRepo.findByEmail(email)
                .map(u -> (Account) u)
                .or(() -> adminRepo.findByEmail(email).map(a -> (Account) a))
                .orElseThrow(() -> new RuntimeException("Account not found"));

        if (!encoder.matches(password, account.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return jwtService.generateToken(account);
    }
}
