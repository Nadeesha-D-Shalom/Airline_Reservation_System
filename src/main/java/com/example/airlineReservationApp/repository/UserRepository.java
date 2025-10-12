package com.example.airlineReservationApp.repository;

import com.example.airlineReservationApp.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
<<<<<<< HEAD
public interface UserRepository extends JpaRepository<UserEntity, Long> {
=======
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
>>>>>>> 4a26394 (Version 1.8.1)
    Optional<UserEntity> findByEmail(String email);
}
