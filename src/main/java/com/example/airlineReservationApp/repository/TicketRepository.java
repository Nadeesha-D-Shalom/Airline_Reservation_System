package com.example.airlineReservationApp.repository;

import com.example.airlineReservationApp.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
