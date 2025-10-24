package com.example.airlineReservationApp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "flights")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "flight_number", nullable = false, length = 50)
    private String flightNumber;

    @Column(name = "airline_name", nullable = false, length = 100)
    private String airlineName;

    @Column(name = "departure_city", nullable = false, length = 100)
    private String departureCity;

    @Column(name = "arrival_city", nullable = false, length = 100)
    private String arrivalCity;

    @Column(name = "departure_time", nullable = false)
    private LocalDateTime departureTime;

    @Column(name = "arrival_time", nullable = false)
    private LocalDateTime arrivalTime;

    @Column(name = "aircraft_type", length = 100)
    private String aircraftType;

    @Column(name = "aircraft_age")
    private Integer aircraftAge;

    @Column(name = "country_of_register", length = 100)
    private String countryOfRegister;

    @Column(name = "serial_number", length = 100)
    private String serialNumber;

    @Column(name = "on_ground", nullable = false)
    private boolean onGround;

    @Column(name = "price")
    private Double price;

    // ----- Getters / Setters -----

    public Long getId() {
        return id;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public String getAircraftType() {
        return aircraftType;
    }

    public Integer getAircraftAge() {
        return aircraftAge;
    }

    public String getCountryOfRegister() {
        return countryOfRegister;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public boolean isOnGround() {
        return onGround;
    }

    public Double getPrice() {
        return price;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setAircraftType(String aircraftType) {
        this.aircraftType = aircraftType;
    }

    public void setAircraftAge(Integer aircraftAge) {
        this.aircraftAge = aircraftAge;
    }

    public void setCountryOfRegister(String countryOfRegister) {
        this.countryOfRegister = countryOfRegister;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setOnGround(boolean onGround) {
        this.onGround = onGround;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
