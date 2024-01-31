package com.solvd.persistence;

import com.solvd.model.Airline;
import com.solvd.persistence.interfaces.AirlineRepository;
import com.solvd.persistence.utility.RepositoryUtility;

import java.util.List;
import java.util.Optional;

public class AirlineRepositoryImpl implements AirlineRepository {

    private static final Class<AirlineRepository> REPOSITORABLE_CLASS = AirlineRepository.class;

    @Override
    public void create(Airline airline) {
        RepositoryUtility.executeVoidSQL(REPOSITORABLE_CLASS, airlineRepository -> airlineRepository.create(airline));

    }

    @Override
    public Optional<Airline> loadById(Long id) {
        return RepositoryUtility.executeTypeSQL(REPOSITORABLE_CLASS, airlineRepository -> airlineRepository.loadById(id));
    }

    @Override
    public List<Airline> loadAll() {
        return RepositoryUtility.executeListSQL(REPOSITORABLE_CLASS, AirlineRepository::loadAll);
    }
}
