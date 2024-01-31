package com.solvd.service;

import com.solvd.model.Airline;
import com.solvd.persistence.AirlineRepositoryImpl;
import com.solvd.persistence.interfaces.AirlineRepository;
import com.solvd.service.serviceinterface.ServiceInterface;
import java.util.List;
import java.util.Optional;

public class AirlineService implements ServiceInterface<Airline, Long> {

    private AirlineRepository airlineRepository = new AirlineRepositoryImpl();

    @Override
    public void creates(Airline airline) {
        airlineRepository.create(airline);
    }

    @Override
    public Optional<Airline> getById(Long id) {
        return airlineRepository.loadById(id);
    }

    @Override
    public List<Airline> getAll() {
        return airlineRepository.loadAll();
    }
}
