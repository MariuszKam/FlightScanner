package com.solvd.service;

import com.solvd.model.Airport;
import com.solvd.persistence.AirportRepositoryImpl;
import com.solvd.persistence.interfaces.AirportRepository;
import com.solvd.service.serviceinterface.ServiceInterface;

import java.util.List;
import java.util.Optional;

public class AirportService implements ServiceInterface<Airport,Long> {

    private AirportRepository airportRepository = new AirportRepositoryImpl();


    @Override
    public void creates(Airport airport) {

        airportRepository.create(airport);

    }
    @Override
    public Optional<Airport> getById(Long id) {

        return airportRepository.loadById(id);
    }
    @Override
    public List<Airport> getAll() {

        return airportRepository.loadAll();
    }
}
