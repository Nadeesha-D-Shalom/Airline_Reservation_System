package com.example.airlineReservationApp.controller;

import com.example.airlineReservationApp.model.AdminEntity;
import com.example.airlineReservationApp.repository.AdminRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminController(AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Create new admin
    @PostMapping("/register")
    public ResponseEntity<?> registerAdmin(@RequestBody AdminEntity admin) {
        if (adminRepository.findByEmail(admin.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Admin already exists with this email");
        }

        admin.setRole("ADMIN");
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        AdminEntity saved = adminRepository.save(admin);
        return ResponseEntity.ok(saved);
    }

    // Get all admins
    @GetMapping("/all")
    public ResponseEntity<List<AdminEntity>> getAllAdmins() {
        return ResponseEntity.ok(adminRepository.findAll());
    }

    // Get admin by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getAdminById(@PathVariable Long id) {
        Optional<AdminEntity> adminOpt = adminRepository.findById(id);
        return adminOpt.<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update admin details
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateAdmin(@PathVariable Long id, @RequestBody AdminEntity updatedAdmin) {
        Optional<AdminEntity> adminOpt = adminRepository.findById(id);

        if (adminOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        AdminEntity admin = adminOpt.get();

        if (updatedAdmin.getName() != null && !updatedAdmin.getName().isBlank()) {
            admin.setName(updatedAdmin.getName());
        }
        if (updatedAdmin.getDepartment() != null && !updatedAdmin.getDepartment().isBlank()) {
            admin.setDepartment(updatedAdmin.getDepartment());
        }
        if (updatedAdmin.getPassword() != null && !updatedAdmin.getPassword().isBlank()) {
            admin.setPassword(passwordEncoder.encode(updatedAdmin.getPassword()));
        }

        AdminEntity saved = adminRepository.save(admin);
        return ResponseEntity.ok(saved);
    }

    // Delete admin by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAdmin(@PathVariable Long id) {
        if (!adminRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        adminRepository.deleteById(id);
        return ResponseEntity.ok("Admin deleted successfully");
    }
}
