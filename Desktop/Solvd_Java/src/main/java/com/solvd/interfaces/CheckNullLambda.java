package com.solvd.interfaces;

@FunctionalInterface
public interface CheckNullLambda<T>  {
    boolean checkNull(T object);
}
