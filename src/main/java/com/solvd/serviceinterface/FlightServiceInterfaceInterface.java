package com.solvd.serviceinterface;

import java.util.List;

public interface FlightServiceInterfaceInterface extends ServiceInterface<Flight> {
    List<String> getDetailedFlightsInfo();

}