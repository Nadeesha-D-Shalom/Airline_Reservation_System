package com.example.airlineReservationApp.repository;

import com.example.airlineReservationApp.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
