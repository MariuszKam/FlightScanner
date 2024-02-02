package com.solvd;

import com.solvd.model.Airport;
import com.solvd.service.AirportService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Main {
    public static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
//        LOGGER.info("Welcome dear passenger");
//        //fetch data
//        FlightScanner.fetchData();
//        //show airport list
//        FlightScanner.printAirports();
//        //select airports
//        FlightScanner.flightSearch();
//        //show result
//        LOGGER.info("Thank you for choosing us");
//        LOGGER.info("bye");

        AirportService airportService = new AirportService();
        Airport airport = airportService.getById(1L).get();
        System.out.println(airport);
    }
}