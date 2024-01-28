package com.solvd.unintegrated.pathfinder.dummy;

public class DummyFlight {
    private String name;
    private Double price;

    private DummyAirport start;
    private DummyAirport destination;

    public DummyFlight(String name, Double price, DummyAirport start, DummyAirport destination) {
        this.name = name;
        this.price = price;
        this.start = start;
        this.destination = destination;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public DummyAirport getStart() {
        return start;
    }

    public DummyAirport getDestination() {
        return destination;
    }
}
