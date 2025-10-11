package com.example.airlineReservationApp.controller;

import com.example.airlineReservationApp.model.AdminEntity;
import com.example.airlineReservationApp.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin
public class AdminController {

    private final AuthService authService;

    public AdminController(AuthService authService) {
        this.authService = authService;
    }

    // Only admin can register another admin
    @PostMapping("/register")
    public AdminEntity registerAdmin(@RequestBody AdminEntity admin) {
        admin.setRole(AdminEntity.Role.ADMIN);
        return (AdminEntity) authService.register(admin);
    }
}
