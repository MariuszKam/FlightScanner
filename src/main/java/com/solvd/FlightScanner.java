package com.solvd;

import com.solvd.model.Airport;
import com.solvd.model.Flight;
import com.solvd.model.RouteDetails;
import com.solvd.service.AirportService;
import com.solvd.service.FlightService;
import com.solvd.service.PathfindingServiceImpl;
import com.solvd.service.serviceinterface.PathfindingService;
import org.apache.commons.lang3.tuple.MutableTriple;
import org.apache.commons.lang3.tuple.Triple;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

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

    public static List<Flight> flightSearchAndSave() {
        Triple<Airport, Airport, Integer> userFlight = getUserInput();
        List<Flight> flightPath = Collections.emptyList();

        if (userFlight != null) {
            Optional<List<Flight>> flightPathOpt = Optional.empty();
            if (userFlight.getRight() == 1) {
                flightPathOpt = pathfindingService.findCheapestPath(userFlight.getLeft(), userFlight.getMiddle());
            } else {
                flightPathOpt = pathfindingService.findShortestPath(userFlight.getLeft(), userFlight.getMiddle());
            }

            if (flightPathOpt.isPresent()) {
                flightPath = flightPathOpt.get();


                List<String> steps = convertRouteToListOfStrings(flightPathOpt);
                steps.forEach(LOGGER::info);

                RouteDetails routeDetails = new RouteDetails(steps);


                String filePath = "src/main/resources/RouteDetails.xml";


                XMLConverter.saveRouteDetailsAsXml(steps, filePath);
                JSONConverter.saveToJSON(routeDetails);

            } else {
                LOGGER.info("No route found.");
            }
        }

        return flightPath;
    }

   private static List<String> convertRouteToListOfStrings(Optional<List<Flight>> optionalFlights) {
        if (optionalFlights.isEmpty()) {
            return Collections.emptyList();
        }

        List<Flight> flights = optionalFlights.get();
        List<String> steps = new ArrayList<>();

        for (Flight flight : flights) {
            String step = String.format("Take flight %s at %s to get to %s",
                    flight.getName(),
                    flight.getStart().getName(),
                    flight.getDestination().getName());
            steps.add(step);
        }

        steps.add("You have reached your final destination");

        return steps;
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
