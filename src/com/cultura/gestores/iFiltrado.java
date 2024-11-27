package com.cultura.gestores;

@FunctionalInterface
public interface iFiltrado<T> {
    boolean filtrar(T elemento);
}
