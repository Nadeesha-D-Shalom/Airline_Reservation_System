package com.example.airlineReservationApp.service;

import com.example.airlineReservationApp.dto.BookingDetailsDTO;
import com.example.airlineReservationApp.model.*;
import com.example.airlineReservationApp.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
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

    // Create new booking with linked passenger, flight, and ticket
    public Booking createBooking(Long flightId,
                                 Passenger passenger,
                                 double totalAmount,
                                 String seatNumber,
                                 String transactionId,
                                 String status) {

        // Find the flight
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new RuntimeException("Flight not found"));

        //  Save passenger first
        passengerRepository.save(passenger);

        // Create ticket
        Ticket ticket = new Ticket();
        ticket.setSeatNumber(seatNumber);
        ticket.setPrice(totalAmount);
        ticket.setStatus(status != null ? status : "PAID");
        ticket.setTicketNumber("TCK-" + System.currentTimeMillis());

        //  Create booking
        Booking booking = new Booking();
        booking.setBookingDate(LocalDate.now());
        booking.setFlight(flight);
        booking.setPassenger(passenger);
        booking.setTransactionId(transactionId);
        booking.setStatus("CONFIRMED");

        //  Link both sides properly
        booking.addTicket(ticket);

        //  Save booking first
        Booking savedBooking = bookingRepository.save(booking);

        //  Save ticket after linking back the booking_id
        ticket.setBooking(savedBooking);
        ticketRepository.save(ticket);

        return savedBooking;
    }

    //  Get all bookings
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    // Get single booking by ID
    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id).orElse(null);
    }

    //  Update booking info
    public Booking updateBooking(Long id, Booking updated) {
        Booking booking = bookingRepository.findById(id).orElse(null);
        if (booking == null) return null;
        booking.setStatus(updated.getStatus());
        booking.setTransactionId(updated.getTransactionId());
        return bookingRepository.save(booking);
    }

    // Delete booking
    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }

    //  Get all booking details with flight + passenger + ticket info
    public List<BookingDetailsDTO> getAllBookingDetails() {
        List<Booking> bookings = bookingRepository.findAll();
        List<BookingDetailsDTO> details = new ArrayList<>();

        for (Booking booking : bookings) {
            BookingDetailsDTO dto = new BookingDetailsDTO();

            // --- Booking Info ---
            dto.setBookingId(booking.getId());
            dto.setBookingDate(booking.getBookingDate() != null ? booking.getBookingDate().toString() : null);
            dto.setTransactionId(booking.getTransactionId());
            dto.setBookingStatus(booking.getStatus());

            // --- Passenger Info ---
            if (booking.getPassenger() != null) {
                dto.setPassengerName(booking.getPassenger().getFullName());
                dto.setEmail(booking.getPassenger().getEmail());
                dto.setPhone(booking.getPassenger().getPhone());
                dto.setNationality(booking.getPassenger().getNationality());
                dto.setPassport(booking.getPassenger().getPassport());
            }

            // --- Flight Info ---
            if (booking.getFlight() != null) {
                Flight f = booking.getFlight();
                dto.setFlightNumber(f.getFlightNumber());
                dto.setAirlineName(f.getAirlineName());
                dto.setDepartureCity(f.getDepartureCity());
                dto.setArrivalCity(f.getArrivalCity());

                //  NEW: Include date/time for departure and arrival
                dto.setDepartureTime(f.getDepartureTime());
                dto.setArrivalTime(f.getArrivalTime());

                // (Optional) you can add duration or aircraft type if needed:
                // dto.setAircraftType(f.getAircraftType());
            }

            // --- Ticket Info ---
            if (booking.getTicket() != null) {
                dto.setSeatNumber(booking.getTicket().getSeatNumber());
                dto.setPrice(booking.getTicket().getPrice());
                dto.setTicketStatus(booking.getTicket().getStatus());
            }

            details.add(dto);
        }

        return details;
    }
}
