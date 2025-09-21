package com.example.airlineReservationApp.service;

import com.example.airlineReservationApp.model.Flight;
import com.example.airlineReservationApp.repository.FlightRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FlightService {

    private final FlightRepository repo;

    public FlightService(FlightRepository repo) {
        this.repo = repo;
    }

    public List<Flight> getAllFlights() {
        return repo.findAll();
    }

    public Flight getFlightById(int id) {
        return repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Flight not found with id: " + id));
    }

    @Transactional
    public Flight createFlight(Flight flight) {
        // example: quick duplicate check by flight number (you can switch to repo.existsSameFlight)
        if (repo.existsByFlightNumber(flight.getFlight_number())) {
            throw new IllegalArgumentException("Flight number already exists");
        }
        if (flight.getArrival_time().isBefore(flight.getDeparture_time())) {
            throw new IllegalArgumentException("Arrival time must be after departure time");
        }
        try {
            return repo.save(flight);
        } catch (DataIntegrityViolationException e) {
            // catches DB unique constraints if you enabled them
            throw new IllegalArgumentException("Duplicate flight detected", e);
        }
    }

    @Transactional
    public Flight updateFlight(int id, Flight updated) {
        Flight existing = getFlightById(id);

        // if number changed, ensure uniqueness (or use existsSameFlightExcludingId if you enabled strict rule)
        if (!existing.getFlight_number().equals(updated.getFlight_number())
                && repo.existsByFlightNumber(updated.getFlight_number())) {
            throw new IllegalArgumentException("Flight number already exists");
        }

        if (updated.getArrival_time().isBefore(updated.getDeparture_time())) {
            throw new IllegalArgumentException("Arrival time must be after departure time");
        }

        existing.setFlight_number(updated.getFlight_number());
        existing.setAircraft_type(updated.getAircraft_type());
        existing.setCountry_of_register(updated.getCountry_of_register());
        existing.setAircraft_AGE(updated.getAircraft_AGE());
        existing.setSerial_number(updated.getSerial_number());
        existing.setAirline_name(updated.getAirline_name());
        existing.setDeparture_city(updated.getDeparture_city());
        existing.setArrival_city(updated.getArrival_city());
        existing.setDeparture_time(updated.getDeparture_time());
        existing.setArrival_time(updated.getArrival_time());
        existing.setOn_ground(updated.isOn_ground());

        try {
            return repo.save(existing);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Duplicate flight detected", e);
        }
    }

    @Transactional
    public void deleteFlight(int id) {
        if (!repo.existsById(id)) {
            throw new IllegalArgumentException("Flight not found with id: " + id);
        }
        repo.deleteById(id);
    }
}
