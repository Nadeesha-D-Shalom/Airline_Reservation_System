package com.example.airlineReservationApp.controller;

import com.example.airlineReservationApp.model.Flight;
import com.example.airlineReservationApp.repository.FlightRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    private final FlightRepository flightRepository;

    public FlightController(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    // Get all flights
    @GetMapping
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    // Search
    @GetMapping("/search")
    public List<Flight> search(@RequestParam("q") String q) {
        return flightRepository.searchFlights(q);
    }

    // Get by ID
    @GetMapping("/{id}")
    public ResponseEntity<Flight> getFlight(@PathVariable Integer id) {
        return flightRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create
    @PostMapping
    public Flight createFlight(@RequestBody Flight flight) {
        return flightRepository.save(flight);
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<Flight> updateFlight(@PathVariable Integer id, @RequestBody Flight flight) {
        return flightRepository.findById(id)
                .map(existing -> {
                    flight.setId(id);
                    return ResponseEntity.ok(flightRepository.save(flight));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFlight(@PathVariable Integer id) {
        return flightRepository.findById(id)
                .map(f -> {
                    flightRepository.delete(f);
                    return ResponseEntity.noContent().build(); // compiles fine now
                })
                .orElse(ResponseEntity.notFound().build());
    }

}
