package com.example.airlineReservationApp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "flights")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @Column(name = "flight_number", length = 10, nullable = false)
    private String flight_number;

    @NotBlank
    @Column(name = "aircraft_type", length = 50, nullable = false)
    private String aircraft_type;

    @NotBlank
    @Column(name = "country_of_register", length = 150, nullable = false)
    private String country_of_register;

    @Min(0)
    @Column(name = "aircraft_AGE", nullable = false)
    private int aircraft_AGE;

    @NotBlank
    @Column(name = "serial_number", length = 75, nullable = false)
    private String serial_number;

    @NotBlank
    @Column(name = "airline_name", length = 75, nullable = false)
    private String airline_name;

    @NotBlank
    @Column(name = "departure_city", length = 50, nullable = false)
    private String departure_city;

    @NotBlank
    @Column(name = "arrival_city", length = 50, nullable = false)
    private String arrival_city;

    @NotNull
    @Column(name = "departure_time", nullable = false)
    private LocalDateTime departure_time;

    @NotNull
    @Column(name = "arrival_time", nullable = false)
    private LocalDateTime arrival_time;

    @Column(name = "on_ground", nullable = false)
    private boolean on_ground;

    public Flight() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getFlight_number() { return flight_number; }
    public void setFlight_number(String flight_number) { this.flight_number = flight_number; }

    public String getAircraft_type() { return aircraft_type; }
    public void setAircraft_type(String aircraft_type) { this.aircraft_type = aircraft_type; }

    public String getCountry_of_register() { return country_of_register; }
    public void setCountry_of_register(String country_of_register) { this.country_of_register = country_of_register; }

    public int getAircraft_AGE() { return aircraft_AGE; }
    public void setAircraft_AGE(int aircraft_AGE) { this.aircraft_AGE = aircraft_AGE; }

    public String getSerial_number() { return serial_number; }
    public void setSerial_number(String serial_number) { this.serial_number = serial_number; }

    public String getAirline_name() { return airline_name; }
    public void setAirline_name(String airline_name) { this.airline_name = airline_name; }

    public String getDeparture_city() { return departure_city; }
    public void setDeparture_city(String departure_city) { this.departure_city = departure_city; }

    public String getArrival_city() { return arrival_city; }
    public void setArrival_city(String arrival_city) { this.arrival_city = arrival_city; }

    public LocalDateTime getDeparture_time() { return departure_time; }
    public void setDeparture_time(LocalDateTime departure_time) { this.departure_time = departure_time; }

    public LocalDateTime getArrival_time() { return arrival_time; }
    public void setArrival_time(LocalDateTime arrival_time) { this.arrival_time = arrival_time; }

    public boolean isOn_ground() { return on_ground; }
    public void setOn_ground(boolean on_ground) { this.on_ground = on_ground; }
}
