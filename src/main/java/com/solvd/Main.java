package com.solvd;

import com.solvd.model.Airline;
import com.solvd.model.Airport;
import com.solvd.model.Flight;
import com.solvd.service.AirlineService;
import com.solvd.service.AirportService;
import com.solvd.service.FlightService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {


        FlightService flightService = new FlightService();
        AirportService airportService= new AirportService();
        AirlineService airlineService = new AirlineService();


        Map<String, List<String>> groupedFlightsInfo = flightService.getDetailedFlightsInfoGroupedByCity();


        for (Map.Entry<String, List<String>> entry : groupedFlightsInfo.entrySet()) {
            System.out.println("City: " + entry.getKey());
            for (String flightInfo : entry.getValue()) {
                System.out.println(" - " + flightInfo);
            }
        }
        airportService.getAll().forEach(System.out::println);

        System.out.println(airportService.getById(2L));
        flightService.getAll().forEach(System.out::println);

        // Initialize with proper values
        Airport startAirport = new Airport(100L,"Warsaw",324d,324d);
        Airport destinationAirport = new Airport(101L,"New York",353d,863d);
        Airline airline = new Airline(100L,"Air");
        Flight newFlight = new Flight(100L,"name",startAirport,destinationAirport,airline,503d);
        airportService.creates(startAirport);
        airportService.creates(destinationAirport);
        airlineService.creates(airline);
        flightService.creates(newFlight);


        Long flightId = 1L;
        Optional<Flight> flightOptional = flightService.getById(flightId);
        flightOptional.ifPresent(flight -> System.out.println("Flight found: " + flight.getName()));


        flightService.getAll().forEach(flight -> System.out.println("Flight: " + flight.getName()));
    }
}