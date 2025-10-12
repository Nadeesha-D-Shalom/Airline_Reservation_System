package com.example.airlineReservationApp.service;

import com.example.airlineReservationApp.model.*;
import com.example.airlineReservationApp.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final FlightRepository flightRepository;
    private final PassengerRepository passengerRepository;
    private final TicketRepository ticketRepository;

    public BookingService(BookingRepository bookingRepository,
                          FlightRepository flightRepository,
                          PassengerRepository passengerRepository,
                          TicketRepository ticketRepository) {
        this.bookingRepository = bookingRepository;
        this.flightRepository = flightRepository;
        this.passengerRepository = passengerRepository;
        this.ticketRepository = ticketRepository;
    }

    public Booking createBooking(Long flightId,
                                 Passenger passenger,
                                 double totalAmount,
                                 String seatNumber,
                                 String transactionId,
                                 String status) {

        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new RuntimeException("Flight not found"));

        passengerRepository.save(passenger);

        Ticket ticket = new Ticket();
        ticket.setSeatNumber(seatNumber);
        ticket.setPrice(totalAmount);
        ticket.setStatus(status != null ? status : "PAID");
        ticket.setTicketNumber("TCK-" + System.currentTimeMillis());
        ticketRepository.save(ticket);

        Booking booking = new Booking();
        booking.setBookingDate(LocalDate.now());
        booking.setFlight(flight);
        booking.setPassenger(passenger);
        booking.addTicket(ticket);
        booking.setTransactionId(transactionId);
        booking.setStatus("CONFIRMED");

        return bookingRepository.save(booking);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id).orElse(null);
    }

    public Booking updateBooking(Long id, Booking updated) {
        Booking booking = bookingRepository.findById(id).orElse(null);
        if (booking == null) return null;
        booking.setStatus(updated.getStatus());
        booking.setTransactionId(updated.getTransactionId());
        return bookingRepository.save(booking);
    }

    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }
}
