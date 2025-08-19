package com.example.airlineReservationApp.controller;

import com.example.airlineReservationApp.dto.FlightDTO;
import com.example.airlineReservationApp.model.Flight;
import com.example.airlineReservationApp.service.FlightService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    private final FlightService svc;

    public FlightController(FlightService svc) {
        this.svc = svc;
    }

    @GetMapping
    public List<FlightDTO> all() {
        return svc.getAllFlights().stream()
                .map(FlightDTO::fromEntity)
                .toList();
    }

    @GetMapping("/{id}")
    public FlightDTO one(@PathVariable int id) {
        return FlightDTO.fromEntity(svc.getFlightById(id));
    }

    @PostMapping
    public FlightDTO create(@Valid @RequestBody Flight f) {
        return FlightDTO.fromEntity(svc.createFlight(f));
    }

    @PutMapping("/{id}")
    public FlightDTO update(@PathVariable int id, @Valid @RequestBody Flight f) {
        return FlightDTO.fromEntity(svc.updateFlight(id, f));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        svc.deleteFlight(id);
    }
}
