package com.solvd.interfaces;

@FunctionalInterface
public interface ConcatenateLambda<T>  {
    T concatenate(T first, T second);
}
