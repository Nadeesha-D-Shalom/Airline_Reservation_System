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

    public List<BookingDetailsDTO> getBookingsByUserEmail(String email) {
        List<Passenger> passengers = passengerRepository.findByEmailIgnoreCase(email);
        List<Booking> userBookings = new ArrayList<>();

        for (Passenger p : passengers) {
            List<Booking> bookings = bookingRepository.findAll();
            for (Booking b : bookings) {
                if (b.getPassenger() != null && b.getPassenger().getId().equals(p.getId())) {
                    userBookings.add(b);
                }
            }
        }
        return mapToBookingDetailsDTO(userBookings);
    }

    public List<BookingDetailsDTO> getAllBookingDetails() {
        List<Booking> bookings = bookingRepository.findAll();
        return mapToBookingDetailsDTO(bookings);
    }

    public BookingDetailsDTO getBookingByTransactionId(String transactionId, String email) {
        List<Booking> bookings = bookingRepository.findAll();

        for (Booking booking : bookings) {
            if (booking.getTransactionId() != null &&
                    booking.getTransactionId().equalsIgnoreCase(transactionId)) {

                if (email == null ||
                        (booking.getPassenger() != null &&
                                booking.getPassenger().getEmail() != null &&
                                booking.getPassenger().getEmail().equalsIgnoreCase(email))) {
                    return mapToBookingDetailsDTO(List.of(booking)).get(0);
                }
            }
        }

        throw new RuntimeException("Booking not found or unauthorized access");
    }


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
}
