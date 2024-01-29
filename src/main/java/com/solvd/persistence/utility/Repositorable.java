package com.solvd.persistence.utility;

import java.util.List;
import java.util.Optional;

public interface Repositorable<T> {
    void create(T t); //Create
    Optional<T> loadById(Long id); //Read
    List<T> loadAll();

}
