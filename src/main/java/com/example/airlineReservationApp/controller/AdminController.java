package com.example.airlineReservationApp.controller;

import com.example.airlineReservationApp.model.AdminEntity;
import com.example.airlineReservationApp.repository.AdminRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {

    private final AdminRepository adminRepository;

    public AdminController(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @PostMapping("/register")
    public AdminEntity registerAdmin(@RequestBody AdminEntity admin) {
        admin.setRole("ADMIN");  // Fixed: directly assign "ADMIN"
        return adminRepository.save(admin);
    }

    @GetMapping("/all")
    public List<AdminEntity> getAllAdmins() {
        return adminRepository.findAll();
    }
}
