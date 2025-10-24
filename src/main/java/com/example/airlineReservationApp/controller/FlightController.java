package com.example.airlineReservationApp.controller;

import com.example.airlineReservationApp.dto.FlightDTO;
import com.example.airlineReservationApp.model.Flight;
import com.example.airlineReservationApp.service.FlightService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flights")
@CrossOrigin(origins = "http://localhost:3000")
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    //  Create (match your frontend: POST /api/flights)
    @PostMapping
    public ResponseEntity<?> addFlight(@RequestBody FlightDTO flightDTO) {
        try {
            Flight flight = flightService.addFlight(flightDTO);
            return ResponseEntity.ok(flight);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to save flight: " + e.getMessage());
        }
    }

    // Read all
    @GetMapping
    public ResponseEntity<List<Flight>> getAllFlights() {
        return ResponseEntity.ok(flightService.getAllFlights());
    }

    // Read one
    @GetMapping("/{id}")
    public ResponseEntity<Flight> getFlightById(@PathVariable Long id) {
        Flight flight = flightService.getFlightById(id);
        return (flight != null) ? ResponseEntity.ok(flight) : ResponseEntity.notFound().build();
    }

    //  Update
    @PutMapping("/{id}")
    public ResponseEntity<?> updateFlight(@PathVariable Long id, @RequestBody FlightDTO dto) {
        try {
            Flight updated = flightService.updateFlight(id, dto);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to update flight: " + e.getMessage());
        }
    }

    //  Safe delete (409 when used)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFlight(@PathVariable Long id) {
        try {
            flightService.deleteFlight(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.status(409).body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to delete flight: " + e.getMessage());
        }
    }

    // Filter by airline name (case-insensitive)
    @GetMapping("/filter/airline/{airline}")
    public ResponseEntity<List<Flight>> filterByAirline(@PathVariable String airline) {
        return ResponseEntity.ok(flightService.filterFlightsByAirline(airline));
    }

    //  Dedicated price update (used by Price Manager page)
    @PutMapping("/{id}/price")
    public ResponseEntity<?> updatePrice(@PathVariable Long id, @RequestParam double price) {
        try {
            Flight flight = flightService.updateFlightPrice(id, price);
            return ResponseEntity.ok(flight);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to update price: " + e.getMessage());
        }
    }
}
