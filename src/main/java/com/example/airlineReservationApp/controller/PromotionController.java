package com.example.airlineReservationApp.controller;

import com.example.airlineReservationApp.dto.PromotionDTO;
import com.example.airlineReservationApp.model.Promotion;
import com.example.airlineReservationApp.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/promotions")
@CrossOrigin(origins = "http://localhost:3000")
public class PromotionController {

    @Autowired
    private PromotionRepository promotionRepository;

    @GetMapping
    public List<PromotionDTO> getAllPromotions() {
        return promotionRepository.findAll()
                .stream()
                .map(PromotionDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @GetMapping("/active")
    public List<PromotionDTO> getActivePromotions() {
        return promotionRepository.findByActiveTrue()
                .stream()
                .map(PromotionDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPromotionById(@PathVariable Long id) {
        return promotionRepository.findById(id)
                .map(p -> ResponseEntity.ok(PromotionDTO.fromEntity(p)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createPromotion(@RequestBody Promotion promotion) {
        Promotion saved = promotionRepository.save(promotion);
        return ResponseEntity.ok(PromotionDTO.fromEntity(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePromotion(@PathVariable Long id, @RequestBody Promotion promotion) {
        return promotionRepository.findById(id)
                .map(existing -> {
                    existing.setTitle(promotion.getTitle());
                    existing.setDescription(promotion.getDescription());
                    existing.setDiscountPercentage(promotion.getDiscountPercentage());
                    existing.setImageUrl(promotion.getImageUrl());
                    existing.setStartDate(promotion.getStartDate());
                    existing.setEndDate(promotion.getEndDate());
                    existing.setActive(promotion.isActive());
                    Promotion updated = promotionRepository.save(existing);
                    return ResponseEntity.ok(PromotionDTO.fromEntity(updated));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePromotion(@PathVariable Long id) {
        if (!promotionRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        promotionRepository.deleteById(id);
        return ResponseEntity.ok("Promotion deleted successfully");
    }
}
