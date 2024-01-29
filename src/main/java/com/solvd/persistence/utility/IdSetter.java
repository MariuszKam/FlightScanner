package com.solvd.persistence.utility;

@FunctionalInterface
public interface IdSetter<T> {
    void setId(T object, Long id);
}
