package com.solvd.persistence;

import com.solvd.model.Flight;
import com.solvd.persistence.interfaces.FlightRepository;
import com.solvd.persistence.utility.Repositorable;
import com.solvd.persistence.utility.RepositoryUtility;

import java.util.List;
import java.util.Optional;

public class FlightRepositoryImpl implements FlightRepository {

    private static final Class<FlightRepository> REPOSITORABLE_CLASS = FlightRepository.class;

    @Override
    public void create(Flight flight) {
        RepositoryUtility.executeVoidSQL(REPOSITORABLE_CLASS, flightRepository -> flightRepository.create(flight));
    }

    @Override
    public Optional<Flight> loadById(Long id) {
        return RepositoryUtility.executeTypeSQL(REPOSITORABLE_CLASS, flightRepository -> flightRepository.loadById(id));
    }

    @Override
    public List<Flight> loadAll() {
        return RepositoryUtility.executeListSQL(REPOSITORABLE_CLASS, FlightRepository::loadAll);
    }
}
