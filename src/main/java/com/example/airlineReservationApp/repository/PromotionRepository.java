package com.example.airlineReservationApp.repository;

import com.example.airlineReservationApp.model.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Long> {
    List<Promotion> findByIsActiveTrue();
    Optional<Promotion> findByPromotionCode(String promotionCode);
    List<Promotion> findByEndDateAfterAndIsActiveTrue(LocalDateTime date);

    long count();
}