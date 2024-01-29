package com.solvd.unintegrated.pathfinder.dummy;

public class DummyFlight {
    private String name;
    private Double price;

    private DummyAirport start;
    private DummyAirport destination;

    public DummyFlight(String name, int price, DummyAirport destination) {
        this.name = name;
        this.price = (double) price;
        this.destination = destination;
    }

    public void setStart(DummyAirport start) {
        this.start = start;
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
