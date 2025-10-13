package com.example.airlineReservationApp.repository;

import com.example.airlineReservationApp.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    @Query("SELECT f FROM Flight f WHERE " +
            "LOWER(f.flightNumber) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(f.departureCity) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(f.arrivalCity) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(f.airlineName) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Flight> searchFlights(@Param("query") String query);
}
