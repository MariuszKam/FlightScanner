package com.solvd;

import com.solvd.model.Airport;
import com.solvd.service.AirportService;
import com.solvd.service.FlightService;
import com.solvd.service.PathfindingServiceImpl;
import com.solvd.service.serviceinterface.PathfindingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Scanner;

public class FlightScanner {
    private static final FlightService flightService = new FlightService();
    private static final AirportService airportService = new AirportService();
    private static final PathfindingService pathfindingService = new PathfindingServiceImpl();
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

        if (type == 1)
            LOGGER.info(pathfindingService.findCheapestPath(airports.get(ap1 - 1), airports.get(ap2 - 1)));
        else
            LOGGER.info(pathfindingService.findShortestPath(airports.get(ap1 - 1), airports.get(ap2 - 1)));
    }

    public static void fetchData() {
        airports = airportService.getAll();
    }
}
