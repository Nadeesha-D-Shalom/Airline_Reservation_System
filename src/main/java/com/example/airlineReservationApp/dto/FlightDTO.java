package com.example.airlineReservationApp.dto;

import com.example.airlineReservationApp.model.Flight;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;

public class FlightDTO {

    private Long id;

    @JsonProperty("flight_number")
    private String flightNumber;

    @JsonProperty("aircraft_type")
    private String aircraftType;

    @JsonProperty("country_of_register")
    private String countryOfRegister;

    @JsonProperty("aircraft_age")
    private int aircraftAge;

    @JsonProperty("serial_number")
    private String serialNumber;

    @JsonProperty("airline_name")
    private String airlineName;

    @JsonProperty("departure_city")
    private String departureCity;

    @JsonProperty("arrival_city")
    private String arrivalCity;

    @JsonProperty("departure_time")
    private LocalDateTime departureTime;

    @JsonProperty("arrival_time")
    private LocalDateTime arrivalTime;

    @JsonProperty("on_ground")
    private boolean onGround;

    public FlightDTO() {}

    public FlightDTO(Long id,
                     String flightNumber,
                     String aircraftType,
                     String countryOfRegister,
                     int aircraftAge,
                     String serialNumber,
                     String airlineName,
                     String departureCity,
                     String arrivalCity,
                     LocalDateTime departureTime,
                     LocalDateTime arrivalTime,
                     boolean onGround) {
        this.id = id;
        this.flightNumber = flightNumber;
        this.aircraftType = aircraftType;
        this.countryOfRegister = countryOfRegister;
        this.aircraftAge = aircraftAge;
        this.serialNumber = serialNumber;
        this.airlineName = airlineName;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.onGround = onGround;
    }

    public static FlightDTO fromEntity(Flight f) {
        if (f == null) return null;
        return new FlightDTO(
                f.getId(),
                f.getFlightNumber(),
                f.getAircraftType(),
                f.getCountryOfRegister(),
                f.getAircraftAge(),
                f.getSerialNumber(),
                f.getAirlineName(),
                f.getDepartureCity(),
                f.getArrivalCity(),
                f.getDepartureTime(),
                f.getArrivalTime(),
                f.isOnGround()
        );
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFlightNumber() { return flightNumber; }
    public void setFlightNumber(String flightNumber) { this.flightNumber = flightNumber; }

    public String getAircraftType() { return aircraftType; }
    public void setAircraftType(String aircraftType) { this.aircraftType = aircraftType; }

    public String getCountryOfRegister() { return countryOfRegister; }
    public void setCountryOfRegister(String countryOfRegister) { this.countryOfRegister = countryOfRegister; }

    public int getAircraftAge() { return aircraftAge; }
    public void setAircraftAge(int aircraftAge) { this.aircraftAge = aircraftAge; }

    public String getSerialNumber() { return serialNumber; }
    public void setSerialNumber(String serialNumber) { this.serialNumber = serialNumber; }

    public String getAirlineName() { return airlineName; }
    public void setAirlineName(String airlineName) { this.airlineName = airlineName; }

    public String getDepartureCity() { return departureCity; }
    public void setDepartureCity(String departureCity) { this.departureCity = departureCity; }

    public String getArrivalCity() { return arrivalCity; }
    public void setArrivalCity(String arrivalCity) { this.arrivalCity = arrivalCity; }

    public LocalDateTime getDepartureTime() { return departureTime; }
    public void setDepartureTime(LocalDateTime departureTime) { this.departureTime = departureTime; }

    public LocalDateTime getArrivalTime() { return arrivalTime; }
    public void setArrivalTime(LocalDateTime arrivalTime) { this.arrivalTime = arrivalTime; }

    public boolean isOnGround() { return onGround; }
    public void setOnGround(boolean onGround) { this.onGround = onGround; }
}
