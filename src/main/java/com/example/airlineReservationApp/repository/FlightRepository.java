package com.example.airlineReservationApp.repository;

import com.example.airlineReservationApp.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    Optional<Flight> findByFlightNumber(String flightNumber);
    List<Flight> findByAirlineNameIgnoreCase(String airlineName);
}
