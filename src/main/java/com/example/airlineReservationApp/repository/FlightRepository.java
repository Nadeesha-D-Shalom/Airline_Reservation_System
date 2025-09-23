package com.example.airlineReservationApp.repository;

import com.example.airlineReservationApp.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FlightRepository extends JpaRepository<Flight, Integer> {

    // Find flight by number
    @Query(value = "SELECT * FROM flights f WHERE f.flight_number = :flightNumber", nativeQuery = true)
    Optional<Flight> findByFlightNumber(@Param("flightNumber") String flightNumber);

    // Check existence
    @Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END FROM flights f WHERE f.flight_number = :flightNumber", nativeQuery = true)
    boolean existsByFlightNumber(@Param("flightNumber") String flightNumber);

    // Search flights (for search bar)
    @Query(value = "SELECT * FROM flights f WHERE " +
            "LOWER(f.flight_number) LIKE LOWER(CONCAT('%', :term, '%')) OR " +
            "LOWER(f.airline_name) LIKE LOWER(CONCAT('%', :term, '%')) OR " +
            "LOWER(f.departure_city) LIKE LOWER(CONCAT('%', :term, '%')) OR " +
            "LOWER(f.arrival_city) LIKE LOWER(CONCAT('%', :term, '%')) OR " +
            "LOWER(f.aircraft_type) LIKE LOWER(CONCAT('%', :term, '%'))", nativeQuery = true)
    List<Flight> searchFlights(@Param("term") String term);
}
