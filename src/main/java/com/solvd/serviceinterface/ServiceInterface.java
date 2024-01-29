package com.solvd.serviceinterface;

import java.util.List;
import java.util.Optional;

public interface ServiceInterface<T> {
    List<T> getAll();
    Optional<T> getById(Long id);
    void create(T item);
}