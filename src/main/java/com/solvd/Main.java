package com.solvd;

public class Main {
    public static void main(String[] args) {
        //fetch data
        FlightScanner.fetchData();
        //show airport list
        FlightScanner.printAirports();
        //select airports
        FlightScanner.flightSearch();
        //show result

    }
}