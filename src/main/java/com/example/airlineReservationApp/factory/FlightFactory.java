package com.example.airlineReservationApp.factory;

import com.example.airlineReservationApp.dto.FlightDTO;
import com.example.airlineReservationApp.model.Flight;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FlightFactory {

    private static final DateTimeFormatter WITH_MINUTES = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
    private static final DateTimeFormatter WITH_SECONDS = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    private static LocalDateTime parseDateTime(String raw) {
        if (raw == null || raw.isBlank()) return null;
        // Support both "yyyy-MM-dd'T'HH:mm" and "yyyy-MM-dd'T'HH:mm:ss"
        try {
            return LocalDateTime.parse(raw, WITH_MINUTES);
        } catch (Exception ignored) { }
        return LocalDateTime.parse(raw, WITH_SECONDS);
    }

    public static Flight createFlight(FlightDTO dto) {
        Flight flight = new Flight();
        flight.setFlightNumber(dto.getFlightNumber());
        flight.setAirlineName(dto.getAirlineName());
        flight.setDepartureCity(dto.getDepartureCity());
        flight.setArrivalCity(dto.getArrivalCity());
        flight.setDepartureTime(parseDateTime(dto.getDepartureTime()));
        flight.setArrivalTime(parseDateTime(dto.getArrivalTime()));
        flight.setAircraftType(dto.getAircraftType());
        flight.setAircraftAge(dto.getAircraftAge());
        flight.setCountryOfRegister(dto.getCountryOfRegister());
        flight.setSerialNumber(dto.getSerialNumber());
        flight.setOnGround(dto.isOnGround());
        flight.setPrice(dto.getPrice());
        return flight;
    }

}
