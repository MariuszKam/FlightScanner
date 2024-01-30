package com.solvd.persistence;

import com.solvd.model.Airport;
import com.solvd.persistence.utility.Repositorable;
import com.solvd.persistence.utility.RepositoryUtility;

import java.util.List;
import java.util.Optional;

public class AirportRepository implements Repositorable {

    private static final Class<Repositorable> REPOSITORABLE_CLASS = Repositorable.class;

    @Override
    public void create(Airport airport) {
        RepositoryUtility.executeVoidSQL(REPOSITORABLE_CLASS, repositorable -> repositorable.create(airport));
    }

    @Override
    public Optional loadById(Long id) {
        return RepositoryUtility.executeTypeSQL(REPOSITORABLE_CLASS, repositorable -> repositorable.loadById(id));
    }

    @Override
    public List loadAll() {
        return RepositoryUtility.executeListSQL(REPOSITORABLE_CLASS, Repositorable::loadAll);
    }
}