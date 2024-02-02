package com.solvd;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Main {
    public static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        LOGGER.info("Welcome dear passenger");
        //fetch data
        FlightScanner.fetchData();
        //show airport list
        FlightScanner.printAirports();
        //select airports
        FlightScanner.flightSearch();
        //show result
        LOGGER.info("Thank you for choosing us");
        LOGGER.info("bye");
    }
}