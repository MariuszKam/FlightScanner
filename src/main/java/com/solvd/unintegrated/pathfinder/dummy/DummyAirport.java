package com.solvd.unintegrated.pathfinder.dummy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DummyAirport {
    private String name;
    private final List<DummyFlight> destinations = new ArrayList<>();
    private double latitude;
    private double longitude;

    public DummyAirport(String name, double latitude, double longitude){
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return this.name;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public List<DummyFlight> getDestinations() {
        return this.destinations;
    }

    public void addDestination(DummyFlight flight) {
        flight.setStart(this);
        this.destinations.add(flight);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DummyAirport that = (DummyAirport) o;
        return Double.compare(latitude, that.latitude) == 0 && Double.compare(longitude, that.longitude) == 0 && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, latitude, longitude);
    }
}
