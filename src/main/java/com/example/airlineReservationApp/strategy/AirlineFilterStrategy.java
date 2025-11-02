package com.example.airlineReservationApp.strategy;

import com.example.airlineReservationApp.model.Flight;
import java.util.List;
import java.util.stream.Collectors;


///  This Concrete Strategy class does the actual work. In my case, it filters the list of flights to
/// only show those that match a selected airline.
///
///
public class AirlineFilterStrategy implements FlightFilterStrategy {

    private final String airline;

    public AirlineFilterStrategy(String airline) {
        this.airline = airline;
    }

    @Override
    public List<Flight> filter(List<Flight> flights) {
        return flights.stream()
                .filter(f -> f.getAirlineName().equalsIgnoreCase(airline))
                .collect(Collectors.toList());
    }
}
