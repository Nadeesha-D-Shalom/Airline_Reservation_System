package com.example.airlineReservationApp.controller;

import com.example.airlineReservationApp.model.LoyaltyPoints;
import com.example.airlineReservationApp.repository.LoyaltyPointsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loyalty")
@CrossOrigin(origins = "http://localhost:3000")
public class LoyaltyController {

    @Autowired
    private LoyaltyPointsRepository loyaltyPointsRepository;

    @PostMapping
    public ResponseEntity<LoyaltyPoints> addPoints(@RequestBody LoyaltyPoints points) {
        LoyaltyPoints saved = loyaltyPointsRepository.save(points);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<List<LoyaltyPoints>> getAll() {
        return ResponseEntity.ok(loyaltyPointsRepository.findAll());
    }

    @GetMapping("/{email}")
    public ResponseEntity<LoyaltyPoints> getByUser(@PathVariable String email) {
        //   findByEmail instead of findByUserEmail
        return loyaltyPointsRepository.findByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
