package com.solvd.service;



import java.util.List;
import java.util.Optional;

public class AirlineService {
    private AirlineRepository airlineRepository;

    public AirlineService(AirlineRepository airlineRepository) {
        this.airlineRepository = airlineRepository;
    }
    @Override
    public void create(Airline airline) {

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
