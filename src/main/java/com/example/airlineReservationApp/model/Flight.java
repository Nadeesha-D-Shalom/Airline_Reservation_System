package com.example.airlineReservationApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "flight")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String flightNumber;
    private String aircraftType;
    private String countryOfRegister;
    private int aircraftAge;
    private String serialNumber;
    private String airlineName;
    private String departureCity;
    private String arrivalCity;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private boolean onGround;
}
