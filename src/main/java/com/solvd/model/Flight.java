package com.solvd.model;

import java.util.Objects;

public class Flight {
    private Long id;
    private String name;
    private Airport start;
    private Airport destination;
    private Airline airline;
    private Double price;

    public Flight(){

    }


    public Flight(Long id,String name, Airport start, Airport destination, Airline airline, Double price) {
        this.id = id;
        this.name = name;
        this.start = start;
        this.destination = destination;
        this.airline = airline;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Airport getStart() {
        return start;
    }

    public void setStart(Airport start) {
        this.start = start;
    }

    public Airport getDestination() {
        return destination;
    }

    public void setDestination(Airport destination) {
        this.destination = destination;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", start=" + start +
                ", destination=" + destination +
                ", airline=" + airline +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Flight flight = (Flight) o;

        return Objects.equals(id, flight.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
