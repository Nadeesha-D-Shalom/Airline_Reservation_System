package com.example.airlineReservationApp.dto;

import com.example.airlineReservationApp.model.Promotion;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class PromotionDTO {
    private Long id;
    private String title;
    private String description;
    private double discountPercentage;
    private String imageUrl;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private boolean active;

    public static PromotionDTO fromEntity(Promotion p) {
        PromotionDTO dto = new PromotionDTO();
        dto.setId(p.getId());
        dto.setTitle(p.getTitle());
        dto.setDescription(p.getDescription());
        dto.setDiscountPercentage(p.getDiscountPercentage());
        dto.setImageUrl(p.getImageUrl());
        dto.setStartDate(p.getStartDate());
        dto.setEndDate(p.getEndDate());
        dto.setActive(p.isActive());
        return dto;
    }
}
