package com.solvd;

import com.solvd.model.Airport;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

public class Main {
    public static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        Airport a1 = new Airport("New York Airport", 40.7167, -74.0);
        Airport a2 = new Airport("Lisbona Airport", 38.7072, -9.1355);
        Airport a3 = new Airport("Madrid Airport", 40.4167, -3.7033);
        Airport a4 = new Airport("London Airport", 51.5002, -0.1262);
        Airport a5 = new Airport("Paris Airport", 48.8567, 2.351);
        Airport a6 = new Airport("Amsterdam Airport", 52.3738, 4.891);
        Airport a7 = new Airport("Frankfurt Airport", 50.116667, 8.683333);
        Airport a8 = new Airport("Instambul Airport", 41.0, 29.0);
        Airport a9 = new Airport("Dubai Airport", 25.266667, 55.333333);
        Airport a10 = new Airport("Tokyo Airport", 35.6785, 139.6823);
        Airport a11 = new Airport("Sao Paulo Airport", -23.5, -46.616667);
        Airport a12 = new Airport("Sydney Airport", -33.866667, 151.2);


        ArrayList<Airport> airports = new ArrayList<>(List.of(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12));

        // List<Airport> airports = AirportServiceImpl.getAll();

        for (int i = 0; i < airports.size(); ++i)
            LOGGER.info("[" + (i + 1) + "] " + airports.get(i) + '\n');

        Function<Scanner, Integer> getInt = (scanner) -> {
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
        };
        Scanner scanner = new Scanner(System.in);
        int ap1 = 0;
        int ap2 = 0;
        int type = 0;

        while (true) {
            ap1 = ap2 = type = 0;

            LOGGER.info("Choose starting airport id");
            for (ap1 = getInt.apply(scanner); ap1 <= 0 || ap1 > airports.size(); ap1 = getInt.apply(scanner))
                LOGGER.info("Wrong input!");


            LOGGER.info("Choose destination airport id");
            for (ap2 = getInt.apply(scanner); ap2 <= 0 || ap2 > airports.size() || ap2 == ap1; ap2 = getInt.apply(scanner))
                LOGGER.info("Wrong input!");


            LOGGER.info("Choose search type,  1 = cheap   2 = fast");
            for (type = getInt.apply(scanner); type <= 0 || type > 2; type = getInt.apply(scanner))
                LOGGER.info("Wrong input!");

            LOGGER.info("From " + airports.get(ap1 - 1) + " to " + airports.get(ap2 - 1) + "\tType = " + type);
            //FlightService.getPath();
        }
    }
}