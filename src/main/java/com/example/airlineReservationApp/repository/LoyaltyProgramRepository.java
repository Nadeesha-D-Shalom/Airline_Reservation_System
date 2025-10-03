package com.example.airlineReservationApp.repository;

import com.example.airlineReservationApp.model.LoyaltyProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LoyaltyProgramRepository extends JpaRepository<LoyaltyProgram, Long> {
    List<LoyaltyProgram> findByIsActiveTrue();
    List<LoyaltyProgram> findByMembershipLevel(String membershipLevel);
}