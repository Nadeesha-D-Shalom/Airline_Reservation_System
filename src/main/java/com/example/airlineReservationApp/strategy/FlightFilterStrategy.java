package com.example.airlineReservationApp.strategy;

import com.example.airlineReservationApp.model.Flight;
import java.util.List;


/// Here The Strategy Pattern change how filter flights without touching the main code
public interface FlightFilterStrategy {
    List<Flight> filter(List<Flight> flights);
}
