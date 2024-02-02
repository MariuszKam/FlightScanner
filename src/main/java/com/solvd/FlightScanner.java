package com.solvd;

import com.solvd.model.Airport;
import com.solvd.model.Flight;
import com.solvd.service.AirportService;
import com.solvd.service.FlightService;
import com.solvd.service.PathfindingServiceImpl;
import com.solvd.service.serviceinterface.PathfindingService;
import org.apache.commons.lang3.tuple.MutableTriple;
import org.apache.commons.lang3.tuple.Triple;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class FlightScanner {
    private static final FlightService flightService = new FlightService();
    private static final AirportService airportService = new AirportService();
    private static final PathfindingService pathfindingService = new PathfindingServiceImpl();
    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    private static List<Airport> airports;

    private static Integer getInt(Scanner scanner) {
        String temp;
        int i = 0;
        int result = 0;

        while (i == 0) {
            temp = scanner.next();
            while (i < temp.length())
                if (Character.isDigit(temp.charAt(i)))
                    i++;
                else break;

            if (i != 0)
                result = Integer.parseInt(temp.substring(0, i));
            else
                LOGGER.info("Wrong input!");
        }
        return result;
    }

    public static void printAirports() {
        for (int i = 0; i < airports.size(); ++i)
            LOGGER.info("[" + (i + 1) + "] " + airports.get(i).getName());
    }

    public static void printGroupedFlights() {
        Map<String, List<String>> groupedFlightsInfo = flightService.getDetailedFlightsInfoGroupedByCity();
        for (var entry : groupedFlightsInfo.entrySet()) {
            LOGGER.info("City: " + entry.getKey() + '\n');

            for (String flightInfo : entry.getValue())
                LOGGER.info(" - " + flightInfo);
            LOGGER.info('\n');
        }
    }

    public static void printFlightSummary(List<Flight> flightPath) {
        if (flightPath.isEmpty())
            return;

        double totalCost = 0;
        String flightNames = new String();
        String airportNames = new String();

        for (var f : flightPath) {
            totalCost += f.getPrice();
            flightNames += f.getName() + " -> ";
            airportNames += f.getStart() + " -> ";
        }

        flightNames = flightNames.substring(0, flightNames.length() - 5);
        airportNames = airportNames.substring(0, airportNames.length() - 5);

        LOGGER.info("Your flight from " + flightPath.getFirst().getName() + " to " + flightPath.getLast().getName() + '\n');
        LOGGER.info(flightNames + '\n');
        LOGGER.info(airportNames + '\n');
        LOGGER.info("Total cost = " + totalCost + "$\n");
    }

    public static Triple<Airport, Airport, Integer> getUserInput() {
        if (airports.size() < 2)
            return null;

        Scanner scanner = new Scanner(System.in);
        int ap1 = 0;
        int ap2 = 0;
        int type = 0;

        LOGGER.info("Choose starting airport id");
        for (ap1 = getInt(scanner); ap1 <= 0 || ap1 > airports.size(); ap1 = getInt(scanner))
            LOGGER.info("Wrong input!");


        LOGGER.info("Choose destination airport id");
        for (ap2 = getInt(scanner); ap2 <= 0 || ap2 > airports.size() || ap2 == ap1; ap2 = getInt(scanner))
            LOGGER.info("Wrong input!");


        LOGGER.info("Choose search type,  1 = cheapest   2 = shortest");
        for (type = getInt(scanner); type <= 0 || type > 2; type = getInt(scanner))
            LOGGER.info("Wrong input!");

        LOGGER.info("From " + airports.get(ap1 - 1) + " to " + airports.get(ap2 - 1) + "\tType = " + type);

        return new MutableTriple<>(airports.get(ap1 - 1), airports.get(ap2 - 1), type);
    }

    public static List<Flight> flightSearch() {
        Triple<Airport, Airport, Integer> userFlight = getUserInput();
        return flightSearch(userFlight);
    }

    public static List<Flight> flightSearch(Triple<Airport, Airport, Integer> userFlight) {
        if (userFlight == null)
            return null;

        if (userFlight.getRight() == 1)
            return pathfindingService.findCheapestPath(userFlight.getLeft(), userFlight.getMiddle()).get();
        else
            return pathfindingService.findShortestPath(userFlight.getLeft(), userFlight.getMiddle()).get();
    }

    public static void fetchData() {
        airports = airportService.getAll();
    }
}
