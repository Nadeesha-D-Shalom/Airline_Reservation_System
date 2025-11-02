package com.example.airlineReservationApp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FlightDTO {

    @JsonProperty("flight_number")
    private String flightNumber;

    @JsonProperty("airline_name")
    private String airlineName;

    @JsonProperty("departure_city")
    private String departureCity;

    @JsonProperty("arrival_city")
    private String arrivalCity;

    @JsonProperty("departure_time")
    private String departureTime;

    @JsonProperty("arrival_time")
    private String arrivalTime;

    @JsonProperty("aircraft_type")
    private String aircraftType;

    @JsonProperty("aircraft_age")
    private Integer aircraftAge;

    @JsonProperty("country_of_register")
    private String countryOfRegister;

    @JsonProperty("serial_number")
    private String serialNumber;

    @JsonProperty("on_ground")
    private boolean onGround;

    @JsonProperty("price")
    private Double price;

    // ----- Getters / Setters -----
    public String getFlightNumber() { return flightNumber; }
    public void setFlightNumber(String flightNumber) { this.flightNumber = flightNumber; }

    public String getAirlineName() { return airlineName; }
    public void setAirlineName(String airlineName) { this.airlineName = airlineName; }

    public String getDepartureCity() { return departureCity; }
    public void setDepartureCity(String departureCity) { this.departureCity = departureCity; }

    public String getArrivalCity() { return arrivalCity; }
    public void setArrivalCity(String arrivalCity) { this.arrivalCity = arrivalCity; }

    public String getDepartureTime() { return departureTime; }
    public void setDepartureTime(String departureTime) { this.departureTime = departureTime; }

    public String getArrivalTime() { return arrivalTime; }
    public void setArrivalTime(String arrivalTime) { this.arrivalTime = arrivalTime; }

    public String getAircraftType() { return aircraftType; }
    public void setAircraftType(String aircraftType) { this.aircraftType = aircraftType; }

    public Integer getAircraftAge() { return aircraftAge; }
    public void setAircraftAge(Integer aircraftAge) { this.aircraftAge = aircraftAge; }

    public String getCountryOfRegister() { return countryOfRegister; }
    public void setCountryOfRegister(String countryOfRegister) { this.countryOfRegister = countryOfRegister; }

    public String getSerialNumber() { return serialNumber; }
    public void setSerialNumber(String serialNumber) { this.serialNumber = serialNumber; }

    public boolean isOnGround() { return onGround; }
    public void setOnGround(boolean onGround) { this.onGround = onGround; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
}
