package com.solvd.persistence;

import com.solvd.model.Airport;
import com.solvd.persistence.interfaces.AirportRepository;
import com.solvd.persistence.utility.Repositorable;
import com.solvd.persistence.utility.RepositoryUtility;

import java.util.List;
import java.util.Optional;

public class AirportRepositoryImpl implements AirportRepository {

    private static final Class<AirportRepository> REPOSITORABLE_CLASS = AirportRepository.class;

    @Override
    public void create(Airport airport) {
        RepositoryUtility.executeVoidSQL(REPOSITORABLE_CLASS, airportRepository -> airportRepository.create(airport));
    }

    @Override
    public Optional<Airport> loadById(Long id) {
        return RepositoryUtility.executeTypeSQL(REPOSITORABLE_CLASS, airportRepository -> airportRepository.loadById(id));
    }

    @Override
    public List<Airport> loadAll() {
        return RepositoryUtility.executeListSQL(REPOSITORABLE_CLASS, AirportRepository::loadAll);
    }
}
