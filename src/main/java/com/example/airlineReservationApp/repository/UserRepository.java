package com.example.airlineReservationApp.repository;

import com.example.airlineReservationApp.model.UserEntity;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    // Find by email (exact match to DB columns)
    @Query(value = "SELECT * FROM users WHERE email = :email", nativeQuery = true)
    Optional<UserEntity> findByEmail(@Param("email") String email);

    // Insert new user (all columns)
    @Modifying
    @Query(value = "INSERT INTO users (email, name, password, role, address, phone_number) " +
            "VALUES (:email, :name, :password, :role, :address, :phoneNumber)",
            nativeQuery = true)
    void insertUser(@Param("email") String email,
                    @Param("name") String name,
                    @Param("password") String password,
                    @Param("role") String role,
                    @Param("address") String address,
                    @Param("phoneNumber") String phoneNumber);

    // Retrieve all users ordered by name
    @Query(value = "SELECT * FROM users ORDER BY name ASC", nativeQuery = true)
    java.util.List<UserEntity> findAllUsers();
}
