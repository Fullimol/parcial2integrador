package com.cultura.gestores;

@FunctionalInterface
public interface iOrdenamiento<T> {
    int ordenar(T elemento1, T elemento2);
}
