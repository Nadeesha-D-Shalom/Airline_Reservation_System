package com.example.airlineReservationApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ticket")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ticketNumber;
    private double price;
    private String seatNumber;
    private String status;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;

    public void setBooking(Booking booking) {
        this.booking = booking;
    }
}
