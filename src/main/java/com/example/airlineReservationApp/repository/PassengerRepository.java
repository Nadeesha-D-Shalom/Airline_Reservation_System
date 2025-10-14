package com.example.airlineReservationApp.repository;

import com.example.airlineReservationApp.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long> {
    List<Passenger> findByEmailIgnoreCase(String email);
}
