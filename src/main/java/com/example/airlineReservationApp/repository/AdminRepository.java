package com.example.airlineReservationApp.repository;

import com.example.airlineReservationApp.model.AdminEntity;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<AdminEntity, Long> {

    // Find by email
    @Query(value = "SELECT * FROM admins WHERE email = :email", nativeQuery = true)
    Optional<AdminEntity> findByEmail(@Param("email") String email);

    // Insert new admin
    @Modifying
    @Query(value = "INSERT INTO admins (email, name, password, role, department) " +
            "VALUES (:email, :name, :password, :role, :department)",
            nativeQuery = true)
    void insertAdmin(@Param("email") String email,
                     @Param("name") String name,
                     @Param("password") String password,
                     @Param("role") String role,
                     @Param("department") String department);

    // Retrieve all admins ordered by name
    @Query(value = "SELECT * FROM admins ORDER BY name ASC", nativeQuery = true)
    java.util.List<AdminEntity> findAllAdmins();
}
