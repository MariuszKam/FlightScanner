package com.solvd.service;


import com.solvd.serviceinterface.FlightServiceInterface;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class FlightService implements FlightServiceInterface {
    private  FlightRepository flightRepository;
    private  AirportRepository airportRepository;
    private  AirlineRepository airlineRepository;

    private SqlSessionFactory sqlSessionFactory;

    public FlightService() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("mybatis-config.xml")) {
            this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (Exception e) {
            throw new RuntimeException("Error initializing SqlSessionFactory", e);
        }
    }
    public FlightService(AirportRepository airportRepository, AirlineRepository airlineRepository, FlightRepository flightRepository) {
        this.airportRepository = airportRepository;
        this.airlineRepository = airlineRepository;
        this.flightRepository = flightRepository;
    }
    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }
    @Override
    public List<String> getDetailedFlightsInfo() {
        List<Airport> airports = airportRepository.loadAll();
        List<Airline> airlines = airlineRepository.loadAll();
        List<Flight> flights = flightRepository.loadAll();

        Map<Long, String> airportIdToNameMap = airports.stream()
                .collect(Collectors.toMap(Airport::getId, Airport::getName));

        Map<Long, String> airlineIdToNameMap = airlines.stream()
                .collect(Collectors.toMap(Airline::getId, Airline::getName));

        return flights.stream().map(flight -> {
            String startAirportName = airportIdToNameMap.getOrDefault(flight.getStart().getName(), "Unknown Airport");
            String destinationAirportName = airportIdToNameMap.getOrDefault(flight.getDestination().getName(), "Unknown Airport");
            String airlineName = airlineIdToNameMap.getOrDefault(flight.getAirline().getName(), "Unknown Airline");

            return String.format("Flight ID: %d, Name: %s, Airline: %s, Start Airport: %s, Destination Airport: %s, Price: %.2f",
                    flight.getId(), flight.getName(), airlineName, startAirportName, destinationAirportName, flight.getPrice());
        }).collect(Collectors.toList());
    }


    @Override
    public void create(Flight flight) {

        flightRepository.create(flight);

    }
    @Override
    public Optional<Flight> getById(Long id) {
        Optional<Flight> flightOptional = flightRepository.loadById(id);
        flightOptional.ifPresent(flight -> {

            String startAirportName = airportRepository.loadById(flight.getStart().getId())
                    .map(Airport::getName)
                    .orElse("Unknown Airport");
            String destinationAirportName = airportRepository.loadById(flight.getDestination().getId())
                    .map(Airport::getName)
                    .orElse("Unknown Airport");

            flight.getStart().setName(startAirportName);
            flight.getDestination().setName(destinationAirportName);
        });
        return flightOptional;
    }
    @Override
    public List<Flight> getAll() {
        List<Flight> flights = flightRepository.loadAll();
        flights.forEach(flight -> {

            String startAirportName = airportRepository.loadById(flight.getStart().getId())
                    .map(Airport::getName)
                    .orElse("Unknown Airport");
            String destinationAirportName = airportRepository.loadById(flight.getDestination().getId())
                    .map(Airport::getName)
                    .orElse("Unknown Airport");

            flight.getStart().setName(startAirportName);
            flight.getDestination().setName(destinationAirportName);
        });
        return flights;
    }
}
