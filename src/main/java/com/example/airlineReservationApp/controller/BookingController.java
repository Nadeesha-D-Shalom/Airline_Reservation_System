package com.example.airlineReservationApp.controller;

import com.example.airlineReservationApp.dto.BookingDetailsDTO;
import com.example.airlineReservationApp.dto.BookingRequest;
import com.example.airlineReservationApp.model.Booking;
import com.example.airlineReservationApp.model.Passenger;
import com.example.airlineReservationApp.service.BookingService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "http://localhost:3000")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

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

    @GetMapping("/user")
    public List<BookingDetailsDTO> getBookingsForUser(@RequestParam String email) {
        return bookingService.getBookingsByUserEmail(email);
    }

    @GetMapping("/tx/{transactionId}")
    public BookingDetailsDTO getBookingByTransactionId(@PathVariable String transactionId,
                                                       @RequestParam(required = false) String email) {
        return bookingService.getBookingByTransactionId(transactionId, email);
    }


    @GetMapping("/details")
    public List<BookingDetailsDTO> getAllBookingDetails() {
        return bookingService.getAllBookingDetails();
    }



}
