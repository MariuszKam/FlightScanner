package com.solvd.service;

import com.solvd.model.Airline;
import com.solvd.persistence.AirlineRepositoryImpl;
import com.solvd.serviceinterface.ServiceInterface;
import java.util.List;
import java.util.Optional;

public class AirlineService implements ServiceInterface<Airline, Long> {

    private final AirlineRepositoryImpl airlineRepositoryImpl = new AirlineRepositoryImpl();

    @Override
    public void creates(Airline airline) {
        airlineRepositoryImpl.create(airline);
    }

    @Override
    public Optional<Airline> getById(Long id) {
        return airlineRepositoryImpl.loadById(id);
    }

    @Override
    public List<Airline> getAll() {
        return airlineRepositoryImpl.loadAll();
    }
}
