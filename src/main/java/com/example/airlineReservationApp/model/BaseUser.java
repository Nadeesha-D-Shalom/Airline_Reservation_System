package com.example.airlineReservationApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // use Long (not Integer)

    @Column(unique = true, nullable = false)
    private String email;

    @Column(name = "full_name", nullable = true)
    private String fullName;


    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role; // USER, ADMIN, etc.
}
