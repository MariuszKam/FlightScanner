package com.solvd.service;



import java.util.List;
import java.util.Optional;

public class AirportService {
    private  AirportRepository airportRepository;

    public AirportService(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }
    @Override
    public void create(Airport airport) {

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
