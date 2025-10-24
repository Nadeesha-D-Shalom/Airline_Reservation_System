package com.example.airlineReservationApp.controller;

import com.example.airlineReservationApp.model.Account;
import com.example.airlineReservationApp.model.UserEntity;
import com.example.airlineReservationApp.repository.AccountRepository;
import com.example.airlineReservationApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/profile")
    public ResponseEntity<?> getUserProfile(@RequestParam String email) {
        Optional<UserEntity> userOpt = userRepository.findByEmail(email);
        if (userOpt.isPresent()) return ResponseEntity.ok(userOpt.get());

        Optional<Account> accountOpt = accountRepository.findByEmail(email);
        if (accountOpt.isPresent()) {
            Account acc = accountOpt.get();
            UserEntity fallback = new UserEntity();
            fallback.setEmail(acc.getEmail());
            fallback.setFullName(acc.getFullName());
            fallback.setRole(acc.getRole());
            return ResponseEntity.ok(fallback);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestParam String email, @RequestBody UserEntity updatedUser) {
        Optional<UserEntity> userOpt = userRepository.findByEmail(email);
        if (userOpt.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");

        UserEntity existing = userOpt.get();

        if (updatedUser.getAddress() != null) existing.setAddress(updatedUser.getAddress());
        if (updatedUser.getPhoneNumber() != null) existing.setPhoneNumber(updatedUser.getPhoneNumber());
        if (updatedUser.getFirstName() != null) existing.setFirstName(updatedUser.getFirstName());
        if (updatedUser.getLastName() != null) existing.setLastName(updatedUser.getLastName());

        String fullName = updatedUser.getFullName();
        if (fullName == null || fullName.isBlank()) {
            fullName = ((existing.getFirstName() != null ? existing.getFirstName() : "") + " "
                    + (existing.getLastName() != null ? existing.getLastName() : "")).trim();
        }
        existing.setFullName(fullName);

        userRepository.save(existing);
        return ResponseEntity.ok(existing);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser(@RequestParam String email) {
        Optional<UserEntity> userOpt = userRepository.findByEmail(email);
        Optional<Account> accOpt = accountRepository.findByEmail(email);

        if (userOpt.isEmpty() && accOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        userOpt.ifPresent(userRepository::delete);
        accOpt.ifPresent(accountRepository::delete);
        return ResponseEntity.ok("User account deleted successfully");
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }
}
