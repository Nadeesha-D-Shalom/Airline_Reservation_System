package com.example.airlineReservationApp.service;

import com.example.airlineReservationApp.dto.BookingDetailsDTO;
import com.example.airlineReservationApp.model.*;
import com.example.airlineReservationApp.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final FlightRepository flightRepository;
    private final PassengerRepository passengerRepository;
    private final TicketRepository ticketRepository;
    private final JwtService jwtService;

    public BookingService(BookingRepository bookingRepository,
                          FlightRepository flightRepository,
                          PassengerRepository passengerRepository,
                          TicketRepository ticketRepository,
                          JwtService jwtService) {
        this.bookingRepository = bookingRepository;
        this.flightRepository = flightRepository;
        this.passengerRepository = passengerRepository;
        this.ticketRepository = ticketRepository;
        this.jwtService = jwtService;
    }

    // Create new booking
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

        Booking booking = new Booking();
        booking.setBookingDate(LocalDate.now());
        booking.setFlight(flight);
        booking.setPassenger(passenger);
        booking.setTransactionId(transactionId);
        booking.setStatus("CONFIRMED");

        booking.addTicket(ticket);
        Booking saved = bookingRepository.save(booking);
        ticket.setBooking(saved);
        ticketRepository.save(ticket);

        return saved;
    }

    // All bookings (for admin)
    public List<BookingDetailsDTO> getAllBookingDetails() {
        List<Booking> bookings = bookingRepository.findAll();
        return mapToBookingDetailsDTO(bookings);
    }

    // Bookings filtered by user email
    public List<BookingDetailsDTO> getBookingsByUserEmail(String email) {
        List<Booking> bookings = bookingRepository.findAll();
        List<Booking> userBookings = new ArrayList<>();

        for (Booking b : bookings) {
            if (b.getPassenger() != null &&
                    b.getPassenger().getEmail() != null &&
                    b.getPassenger().getEmail().equalsIgnoreCase(email)) {
                userBookings.add(b);
            }
        }

        return mapToBookingDetailsDTO(userBookings);
    }

    //  Optional: If JWT version used
    public List<BookingDetailsDTO> getBookingsForCurrentUser(HttpServletRequest request) {
        String email = jwtService.extractEmailFromToken(request);
        return getBookingsByUserEmail(email);
    }

    // Shared DTO Mapper
    private List<BookingDetailsDTO> mapToBookingDetailsDTO(List<Booking> bookings) {
        List<BookingDetailsDTO> list = new ArrayList<>();

        for (Booking booking : bookings) {
            BookingDetailsDTO dto = new BookingDetailsDTO();
            dto.setBookingId(booking.getId());
            dto.setBookingDate(booking.getBookingDate() != null ? booking.getBookingDate().toString() : null);
            dto.setTransactionId(booking.getTransactionId());
            dto.setBookingStatus(booking.getStatus());

            if (booking.getPassenger() != null) {
                dto.setPassengerName(booking.getPassenger().getFullName());
                dto.setEmail(booking.getPassenger().getEmail());
                dto.setPhone(booking.getPassenger().getPhone());
                dto.setNationality(booking.getPassenger().getNationality());
                dto.setPassport(booking.getPassenger().getPassport());
            }

            if (booking.getFlight() != null) {
                Flight f = booking.getFlight();
                dto.setFlightNumber(f.getFlightNumber());
                dto.setAirlineName(f.getAirlineName());
                dto.setDepartureCity(f.getDepartureCity());
                dto.setArrivalCity(f.getArrivalCity());
                dto.setDepartureTime(f.getDepartureTime());
                dto.setArrivalTime(f.getArrivalTime());
            }

            if (booking.getTicket() != null) {
                dto.setSeatNumber(booking.getTicket().getSeatNumber());
                dto.setPrice(booking.getTicket().getPrice());
                dto.setTicketStatus(booking.getTicket().getStatus());
            }

            list.add(dto);
        }

        return list;
    }

    // CRUD Helpers
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
