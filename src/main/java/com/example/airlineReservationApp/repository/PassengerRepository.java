package com.example.airlineReservationApp.repository;

import com.example.airlineReservationApp.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passenger, Long> { }
