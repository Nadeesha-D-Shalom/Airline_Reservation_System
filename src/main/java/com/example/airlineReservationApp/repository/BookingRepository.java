package com.example.airlineReservationApp.repository;

import com.example.airlineReservationApp.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    // All bookings created for a passenger email (case-insensitive)
    List<Booking> findAllByPassenger_EmailIgnoreCase(String email);

    // Single booking lookup by transaction id
    Optional<Booking> findByTransactionId(String transactionId);
}
