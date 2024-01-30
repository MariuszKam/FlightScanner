package com.solvd.serviceinterface;

import java.util.List;
import java.util.Optional;

public interface ServiceInterface<T, ID> {
    List<T> getAll();
    Optional<T> getById(Long id);
    void creates(T item);
}