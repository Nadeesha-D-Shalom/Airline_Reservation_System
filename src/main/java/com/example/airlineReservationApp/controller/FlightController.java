package com.example.airlineReservationApp.controller;

import com.example.airlineReservationApp.dto.FlightDTO;
import com.example.airlineReservationApp.model.Flight;
import com.example.airlineReservationApp.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/flights")
@CrossOrigin(origins = "http://localhost:3000")
public class FlightController {

    @Autowired
    private FlightRepository flightRepository;

    // Add new flight
    @PostMapping
    public ResponseEntity<?> addFlight(@RequestBody FlightDTO flightDTO) {
        Flight savedFlight = flightRepository.save(flightDTO.toEntity());
        return ResponseEntity.ok(FlightDTO.fromEntity(savedFlight));
    }

    // Get all flights
    @GetMapping
    public List<FlightDTO> getAllFlights() {
        return flightRepository.findAll()
                .stream()
                .map(FlightDTO::fromEntity)
                .collect(Collectors.toList());
    }

    // Get flight by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getFlightById(@PathVariable Long id) {
        return flightRepository.findById(id)
                .map(flight -> ResponseEntity.ok(FlightDTO.fromEntity(flight)))
                .orElse(ResponseEntity.notFound().build());
    }

    // Search flights
    @GetMapping("/search")
    public List<FlightDTO> searchFlights(@RequestParam("q") String query) {
        return flightRepository.searchFlights(query)
                .stream()
                .map(FlightDTO::fromEntity)
                .collect(Collectors.toList());
    }

    // Update a flight
    @PutMapping("/{id}")
    public ResponseEntity<?> updateFlight(@PathVariable Long id, @RequestBody FlightDTO flightDTO) {
        return flightRepository.findById(id)
                .map(existing -> {
                    existing.setFlightNumber(flightDTO.getFlightNumber());
                    existing.setAircraftType(flightDTO.getAircraftType());
                    existing.setCountryOfRegister(flightDTO.getCountryOfRegister());
                    existing.setAircraftAge(flightDTO.getAircraftAge());
                    existing.setSerialNumber(flightDTO.getSerialNumber());
                    existing.setAirlineName(flightDTO.getAirlineName());
                    existing.setDepartureCity(flightDTO.getDepartureCity());
                    existing.setArrivalCity(flightDTO.getArrivalCity());
                    existing.setDepartureTime(flightDTO.getDepartureTime());
                    existing.setArrivalTime(flightDTO.getArrivalTime());
                    existing.setOnGround(flightDTO.isOnGround());
                    Flight updated = flightRepository.save(existing);
                    return ResponseEntity.ok(FlightDTO.fromEntity(updated));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    //  Delete a flight
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFlight(@PathVariable Long id) {
        if (!flightRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        flightRepository.deleteById(id);
        return ResponseEntity.ok("Flight deleted successfully.");
    }
}
