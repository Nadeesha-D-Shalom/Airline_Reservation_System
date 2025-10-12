package com.example.airlineReservationApp.repository;

import com.example.airlineReservationApp.model.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<AdminEntity, Long> {
}
