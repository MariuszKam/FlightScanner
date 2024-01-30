package com.solvd;

import com.solvd.model.Flight;
import com.solvd.service.AirportService;
import com.solvd.service.FlightService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {


        FlightService flightService = new FlightService();
        AirportService airportService= new AirportService();



        Map<String, List<String>> groupedFlightsInfo = flightService.getDetailedFlightsInfoGroupedByCity();


        for (Map.Entry<String, List<String>> entry : groupedFlightsInfo.entrySet()) {
            System.out.println("City: " + entry.getKey());
            for (String flightInfo : entry.getValue()) {
                System.out.println(" - " + flightInfo);
            }
        }
        airportService.getAll().forEach(System.out::println);

        System.out.println(airportService.getById(2L));
      //  flightService.getAll().forEach(System.out::println);

      //  Flight newFlight = new Flight();
       // flightService.creates(newFlight);


        Long flightId = 1L;
        Optional<Flight> flightOptional = flightService.getById(flightId);
        flightOptional.ifPresent(flight -> System.out.println("Flight found: " + flight.getName()));


        flightService.getAll().forEach(flight -> System.out.println("Flight: " + flight.getName()));
    }
}