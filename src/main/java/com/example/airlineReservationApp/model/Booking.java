package com.example.airlineReservationApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "booking")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate bookingDate;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;

    @ManyToOne
    @JoinColumn(name = "passenger_id")
    private Passenger passenger;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ticket_id", referencedColumnName = "id")
    private Ticket ticket;

    private String status; // CONFIRMED, CANCELLED, PENDING

    private String transactionId;

    public void addTicket(Ticket ticket) {
        this.ticket = ticket;
        ticket.setBooking(this);
    }
}
