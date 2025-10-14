package com.example.airlineReservationApp.controller;

import com.example.airlineReservationApp.dto.BookingDetailsDTO;
import com.example.airlineReservationApp.dto.BookingRequest;
import com.example.airlineReservationApp.model.Booking;
import com.example.airlineReservationApp.model.Passenger;
import com.example.airlineReservationApp.service.BookingService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "http://localhost:3000")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    //  Create new booking
    @PostMapping
    public Booking createBooking(@RequestBody BookingRequest request) {
        if (request.getPassengers() == null || request.getPassengers().isEmpty()) {
            throw new IllegalArgumentException("Passenger details required");
        }

        BookingRequest.PassengerPayload p = request.getPassengers().get(0);
        Passenger passenger = new Passenger();
        passenger.setFullName(p.getFullName());
        passenger.setDob(p.getDob());
        passenger.setGender(p.getGender());
        passenger.setNationality(p.getNationality());
        passenger.setEmail(p.getEmail());
        passenger.setPhone(p.getPhone());
        passenger.setPassport(p.getPassport());
        passenger.setFlyerNumber(p.getFlyerNumber());
        passenger.setAssistance(p.getAssistance());
        passenger.setDietary(p.getDietary());
        passenger.setInsurance(p.isInsurance());
        passenger.setBaggage(p.getBaggage());
        passenger.setEmergencyName(p.getEmergencyName());
        passenger.setEmergencyPhone(p.getEmergencyPhone());

        return bookingService.createBooking(
                request.getFlightId(),
                passenger,
                request.getTotalAmount(),
                p.getSeat(),
                request.getTransactionId(),
                request.getStatus()
        );
    }

    //  Get all bookings (ADMIN)
    @GetMapping("/details")
    public List<BookingDetailsDTO> getAllBookingDetails() {
        return bookingService.getAllBookingDetails();
    }

    //  Get bookings only for logged-in user (email param version)
    @GetMapping("/user")
    public List<BookingDetailsDTO> getBookingsForUser(@RequestParam String email) {
        return bookingService.getBookingsByUserEmail(email);
    }

    // Optional: If JWT version used
    /*
    @GetMapping("/my")
    public List<BookingDetailsDTO> getBookingsForCurrentUser(HttpServletRequest request) {
        return bookingService.getBookingsForCurrentUser(request);
    }
    */

    //  Get one booking by ID
    @GetMapping("/{id}")
    public Booking getBookingById(@PathVariable Long id) {
        return bookingService.getBookingById(id);
    }

    // Update booking
    @PutMapping("/{id}")
    public Booking updateBooking(@PathVariable Long id, @RequestBody Booking booking) {
        return bookingService.updateBooking(id, booking);
    }

    //  Delete booking
    @DeleteMapping("/{id}")
    public void deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
    }
}
