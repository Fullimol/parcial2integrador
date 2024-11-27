package com.cultura.gestores;

import java.util.List;
import java.util.stream.Collectors;

public class Ordenamiento<T> {

    // Filtrar elementos según un criterio de filtro
    public List<T> filtrar(List<T> elementos, iFiltrado<T> filtro) {
        return elementos.stream()
                .filter(filtro::filtrar)
                .collect(Collectors.toList());
    }

    // Ordenar elementos según un criterio de ordenamiento
    public List<T> ordenar(List<T> elementos, iOrdenamiento<T> criterio) {
        return elementos.stream()
                .sorted(criterio::ordenar)
                .collect(Collectors.toList());
    }
}
