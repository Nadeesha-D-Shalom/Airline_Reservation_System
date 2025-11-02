package com.example.airlineReservationApp.service;

import com.example.airlineReservationApp.dto.FlightDTO;
import com.example.airlineReservationApp.factory.FlightFactory;
import com.example.airlineReservationApp.model.Flight;
import com.example.airlineReservationApp.repository.BookingRepository;
import com.example.airlineReservationApp.repository.FlightRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class FlightService {

    private final FlightRepository flightRepository;
    private final BookingRepository bookingRepository;

    public FlightService(FlightRepository flightRepository, BookingRepository bookingRepository) {
        this.flightRepository = flightRepository;
        this.bookingRepository = bookingRepository;
    }

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public Flight getFlightById(Long id) {
        return flightRepository.findById(id).orElse(null);
    }

    @Transactional
    public Flight addFlight(FlightDTO dto) {
        validateDateOrder(dto.getDepartureTime(), dto.getArrivalTime());
        Flight flight = FlightFactory.createFlight(dto);
        return flightRepository.save(flight);
    }

    @Transactional
    public Flight updateFlight(Long id, FlightDTO dto) {
        validateDateOrder(dto.getDepartureTime(), dto.getArrivalTime());
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Flight not found"));

        // map fields
        flight.setFlightNumber(dto.getFlightNumber());
        flight.setAirlineName(dto.getAirlineName());
        flight.setDepartureCity(dto.getDepartureCity());
        flight.setArrivalCity(dto.getArrivalCity());

        // parse dates like factory
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime dep = parse(dto.getDepartureTime(), fmt);
        LocalDateTime arr = parse(dto.getArrivalTime(), fmt);

        flight.setDepartureTime(dep);
        flight.setArrivalTime(arr);

        flight.setAircraftType(dto.getAircraftType());
        flight.setAircraftAge(dto.getAircraftAge());
        flight.setCountryOfRegister(dto.getCountryOfRegister());
        flight.setSerialNumber(dto.getSerialNumber());
        flight.setOnGround(dto.isOnGround());
        flight.setPrice(dto.getPrice());

        return flightRepository.save(flight);
    }

    private static LocalDateTime parse(String s, DateTimeFormatter fmt) {
        if (s == null || s.isBlank()) return null;
        try { return LocalDateTime.parse(s, fmt); }
        catch (Exception e) { return LocalDateTime.parse(s); }
    }

    private static void validateDateOrder(String dep, String arr) {
        if (dep == null || arr == null) return;
        LocalDateTime d = parse(dep, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
        LocalDateTime a = parse(arr, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
        if (d != null && a != null && !a.isAfter(d)) {
            throw new IllegalArgumentException("Arrival time must be after the departure time.");
        }
        if (d != null && d.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Departure time cannot be in the past.");
        }
    }

    @Transactional
    public void deleteFlight(Long id) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Flight not found"));

        if (bookingRepository.existsByFlight(flight)) {
            // This message is sent to client with 409 from controller
            throw new IllegalStateException("Cannot delete because this flight is used by users.");
        }
        flightRepository.delete(flight);
    }

    public List<Flight> filterFlightsByAirline(String airline) {
        return flightRepository.findByAirlineNameIgnoreCase(airline);
    }

    @Transactional
    public Flight updateFlightPrice(Long id, double price) {
        if (price < 0) throw new IllegalArgumentException("Price cannot be negative.");
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Flight not found"));
        flight.setPrice(price);
        return flightRepository.save(flight);
    }
}
