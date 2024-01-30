package com.solvd.service;

import com.solvd.model.Flight;
import com.solvd.persistence.FlightRepositoryImpl;
import com.solvd.serviceinterface.ServiceInterface;
import com.solvd.model.Airport;
import com.solvd.model.Airline;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Map;

public class FlightService implements ServiceInterface<Flight, Long> {

    private final FlightRepositoryImpl flightRepositoryImpl = new FlightRepositoryImpl();
    private final AirportService airportService = new AirportService();
    private final AirlineService airlineService = new AirlineService();


    public Map<String, List<String>> getDetailedFlightsInfoGroupedByCity() {
        List<Flight> flights = flightRepositoryImpl.loadAll();
        List<Airport> airports = airportService.getAll();
        List<Airline> airlines = airlineService.getAll();


        Map<Long, String> airportIdToNameMap = airports.stream()
                .collect(Collectors.toMap(Airport::getId, Airport::getName));
        Map<Long, String> airlineIdToNameMap = airlines.stream()
                .collect(Collectors.toMap(Airline::getId, Airline::getName));

        return flights.stream()
                .collect(Collectors.groupingBy(
                        flight -> airportIdToNameMap.getOrDefault(
                                flight.getStart() != null ? flight.getStart().getId() : null, "Unknown Airport"),
                        Collectors.mapping(flight -> formatFlightInfo(flight, airportIdToNameMap, airlineIdToNameMap), Collectors.toList())
                ));
    }

    private String formatFlightInfo(Flight flight, Map<Long, String> airportIdToNameMap, Map<Long, String> airlineIdToNameMap) {
        String startAirportName = (flight.getStart() != null && flight.getStart().getId() != null) ?
                airportIdToNameMap.getOrDefault(flight.getStart().getId(), "Unknown Airport") : "Unknown Airport";

        String destinationAirportName = (flight.getDestination() != null && flight.getDestination().getId() != null) ?
                airportIdToNameMap.getOrDefault(flight.getDestination().getId(), "Unknown Airport") : "Unknown Airport";

        String airlineName = (flight.getAirline() != null && flight.getAirline().getId() != null) ?
                airlineIdToNameMap.getOrDefault(flight.getAirline().getId(), "Unknown Airline") : "Unknown Airline";

        return String.format("Flight ID: %d, Name: %s, Airline: %s, Start Airport: %s, Destination Airport: %s, Price: %.2f",
                flight.getId(), flight.getName(), airlineName, startAirportName, destinationAirportName, flight.getPrice());
    }

    @Override
    public void creates(Flight flight) {
        flightRepositoryImpl.create(flight);
    }

    @Override
    public Optional<Flight> getById(Long id) {
        Optional<Flight> flightOptional = flightRepositoryImpl.loadById(id);
        flightOptional.ifPresent(flight -> {
            String startAirportName = flight.getStart() != null ?
                    airportService.getById(flight.getStart().getId()).map(Airport::getName).orElse("Unknown Airport") : "Unknown Airport";
            String destinationAirportName = flight.getDestination() != null ?
                    airportService.getById(flight.getDestination().getId()).map(Airport::getName).orElse("Unknown Airport") : "Unknown Airport";

            flight.getStart().setName(startAirportName);
            flight.getDestination().setName(destinationAirportName);
        });
        return flightOptional;
    }
    @Override
    public List<Flight> getAll() {

        return flightRepositoryImpl.loadAll();
    }
   /* public List<Flight> getAll() {
        List<Flight> flights = flightRepositoryImpl.loadAll();
        flights.forEach(flight -> {
            String startAirportName = flight.getStart() != null ?
                    airportService.getById(flight.getStart().getId()).map(Airport::getName).orElse("Unknown Airport") : "Unknown Airport";
            String destinationAirportName = flight.getDestination() != null ?
                    airportService.getById(flight.getDestination().getId()).map(Airport::getName).orElse("Unknown Airport") : "Unknown Airport";

            flight.getStart().setName(startAirportName);
            flight.getDestination().setName(destinationAirportName);
        });
        return flights;
    }*/
}
