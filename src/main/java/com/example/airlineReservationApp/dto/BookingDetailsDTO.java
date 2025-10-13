package com.example.airlineReservationApp.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookingDetailsDTO {
    private Long bookingId;
    private String bookingDate;
    private String transactionId;
    private String bookingStatus;

    private String passengerName;
    private String email;
    private String phone;
    private String nationality;
    private String passport;

    private String flightNumber;
    private String airlineName;
    private String departureCity;
    private String arrivalCity;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;

    private String seatNumber;
    private double price;
    private String ticketStatus;
}
