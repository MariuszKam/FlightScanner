package com.solvd.serviceinterface;

import java.util.List;

public interface FlightServiceInterface extends ServiceInterface<Flight> {
    List<String> getDetailedFlightsInfo();

}