package com.example.airlineReservationApp.controller;

import com.example.airlineReservationApp.model.UserEntity;
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

<<<<<<< HEAD
    //  Get user profile by email
=======
>>>>>>> ac6aa17 (Version 1.9.1)
    @GetMapping("/profile")
    public ResponseEntity<?> getUserProfile(@RequestParam String email) {
        Optional<UserEntity> userOpt = userRepository.findByEmail(email);

        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

<<<<<<< HEAD
        return ResponseEntity.ok(userOpt.get());
    }

    //  Update user profile
    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestParam String email, @RequestBody UserEntity updatedUser) {
        Optional<UserEntity> userOpt = userRepository.findByEmail(email);

        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        UserEntity existingUser = userOpt.get();

        // --- Update only if non-null/non-blank values are provided ---
        if (updatedUser.getAddress() != null && !updatedUser.getAddress().isBlank()) {
            existingUser.setAddress(updatedUser.getAddress());
        }

        if (updatedUser.getPhoneNumber() != null && !updatedUser.getPhoneNumber().isBlank()) {
            existingUser.setPhoneNumber(updatedUser.getPhoneNumber());
        }

        // Update first & last names
        if (updatedUser.getFirstName() != null && !updatedUser.getFirstName().isBlank()) {
            existingUser.setFirstName(updatedUser.getFirstName());
        }

        if (updatedUser.getLastName() != null && !updatedUser.getLastName().isBlank()) {
            existingUser.setLastName(updatedUser.getLastName());
        }

        //  Update or recompute full name safely
        if (updatedUser.getFullName() != null && !updatedUser.getFullName().isBlank()) {
            existingUser.setFullName(updatedUser.getFullName());
        } else {
            String combinedName = (existingUser.getFirstName() != null ? existingUser.getFirstName() : "")
                    + (existingUser.getLastName() != null ? " " + existingUser.getLastName() : "");
            existingUser.setFullName(combinedName.trim());
        }

        userRepository.save(existingUser);
        return ResponseEntity.ok(existingUser);
    }
=======
        // Return actual user data (not wrapped in Optional)
        return ResponseEntity.ok(userOpt.get());
    }
>>>>>>> ac6aa17 (Version 1.9.1)
}
