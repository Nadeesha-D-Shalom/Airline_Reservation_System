package com.example.airlineReservationApp.repository;

import com.example.airlineReservationApp.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Explicit JPQL using snake_case entity fields to avoid derived-name issues.
 */
public interface FlightRepository extends JpaRepository<Flight, Integer> {

    // exact match on flight_number
    @Query("select f from Flight f where f.flight_number = :flightNumber")
    Optional<Flight> findByFlightNumber(@Param("flightNumber") String flightNumber);

    @Query("select (count(f) > 0) from Flight f where f.flight_number = :flightNumber")
    boolean existsByFlightNumber(@Param("flightNumber") String flightNumber);

    // OPTIONAL: stronger duplicate prevention (same airline + number + departure_time)
    @Query("""
           select (count(f) > 0)
           from Flight f
           where upper(f.airline_name) = upper(:airline)
             and upper(f.flight_number) = upper(:flightNumber)
             and f.departure_time = :departureTime
           """)
    boolean existsSameFlight(@Param("airline") String airline,
                             @Param("flightNumber") String flightNumber,
                             @Param("departureTime") LocalDateTime departureTime);

    @Query("""
           select (count(f) > 0)
           from Flight f
           where upper(f.airline_name) = upper(:airline)
             and upper(f.flight_number) = upper(:flightNumber)
             and f.departure_time = :departureTime
             and f.id <> :excludeId
           """)
    boolean existsSameFlightExcludingId(@Param("airline") String airline,
                                        @Param("flightNumber") String flightNumber,
                                        @Param("departureTime") LocalDateTime departureTime,
                                        @Param("excludeId") int excludeId);
}
