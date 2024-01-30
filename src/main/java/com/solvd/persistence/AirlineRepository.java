package com.solvd.persistence;

import com.solvd.model.Airline;
import com.solvd.persistence.utility.Repositorable;
import com.solvd.persistence.utility.RepositoryUtility;

import java.util.List;
import java.util.Optional;

public class AirlineRepository implements Repositorable<Airline> {

    private static final Class<Repositorable> REPOSITORABLE_CLASS = Repositorable.class;

    @Override
    public void create(Airline airline) {
        RepositoryUtility.executeVoidSQL(REPOSITORABLE_CLASS, repositorable -> repositorable.create(airline));

    }

    @Override
    public Optional<Airline> loadById(Long id) {
        return RepositoryUtility.executeTypeSQL(REPOSITORABLE_CLASS, repositorable -> repositorable.loadById(id));
    }

    @Override
    public List<Airline> loadAll() {
        return RepositoryUtility.executeListSQL(REPOSITORABLE_CLASS, Repositorable::loadAll);
    }
}
