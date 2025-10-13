package com.example.airlineReservationApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "passenger")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String dob;
    private String gender;
    private String nationality;
    private String email;
    private String phone;
    private String passport;
    private String flyerNumber;
    private String assistance;
    private String dietary;
    private boolean insurance;
    private String baggage;
    private String emergencyName;
    private String emergencyPhone;
}
