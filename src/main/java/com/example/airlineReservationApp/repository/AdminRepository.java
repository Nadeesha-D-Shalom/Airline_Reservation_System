package com.example.airlineReservationApp.repository;

import com.example.airlineReservationApp.model.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
<<<<<<< HEAD
public interface AdminRepository extends JpaRepository<AdminEntity, Long> {
=======
public interface AdminRepository extends JpaRepository<AdminEntity, Integer> {
    Optional<AdminEntity> findByEmail(String email);
>>>>>>> 4a26394 (Version 1.8.1)
}
