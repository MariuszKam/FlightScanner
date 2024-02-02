package com.solvd;

import com.solvd.model.Airport;
import com.solvd.model.Flight;
import com.solvd.model.RouteDetails;
import com.solvd.service.AirportService;
import com.solvd.service.FlightService;
import com.solvd.service.PathfindingServiceImpl;
import com.solvd.service.serviceinterface.PathfindingService;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.*;

public class FlightScanner {
    private static final FlightService flightService = new FlightService();
    private static final AirportService airportService = new AirportService();
    private static final PathfindingService pathfindingService = new PathfindingServiceImpl(flightService);
    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    private static List<Airport> airports = airportService.getAll();

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
            LOGGER.info("[" + (i + 1) + "] " + airports.get(i) + '\n');
    }

    public static void flightSearch() {
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

        if (type == 1) {
            Optional<List<Flight>> route = pathfindingService.findCheapestPath(airports.get(ap1 - 1), airports.get(ap2 - 1));

            List<String> steps = convertRouteToListOfStrings(route);
            saveRouteDetailsAsXml(steps, "main/resources/RouteDetails.xml");
        } else {
            Optional<List<Flight>> route = pathfindingService.findShortestPath(airports.get(ap1 - 1), airports.get(ap2 - 1));

            List<String> steps = convertRouteToListOfStrings(route);
            saveRouteDetailsAsXml(steps, "main/resources/RouteDetails.xml");
        }
    }

    public static void fetchData() {
        airports = airportService.getAll();
    }

    private static void saveRouteDetailsAsXml(List<String> steps, String filePath) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(RouteDetails.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            RouteDetails routeDetails = new RouteDetails(steps);
            marshaller.marshal(routeDetails, new File(filePath));
        } catch (Exception e) {
            LOGGER.error("Error saving route details to XML", e);
        }
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

}
