package com.example.airlineReservationApp.repository;

import com.example.airlineReservationApp.model.LoyaltyPoints;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface LoyaltyPointsRepository extends JpaRepository<LoyaltyPoints, Long> {
    Optional<LoyaltyPoints> findByEmail(String email);
}
