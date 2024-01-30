package com.solvd.service;

import com.solvd.model.Airport;
import com.solvd.persistence.AirportRepositoryImpl;
import com.solvd.serviceinterface.ServiceInterface;

import java.util.List;
import java.util.Optional;

public class AirportService implements ServiceInterface<Airport,Long> {

    private final AirportRepositoryImpl airportRepositoryImpl = new AirportRepositoryImpl();


    @Override
    public void creates(Airport airport) {

        airportRepositoryImpl.create(airport);

    }
    @Override
    public Optional<Airport> getById(Long id) {

        return airportRepositoryImpl.loadById(id);
    }
    @Override
    public List<Airport> getAll() {

        return airportRepositoryImpl.loadAll();
    }
}
