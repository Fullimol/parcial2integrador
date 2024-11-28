package com.cultura.gestores;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Clase genérica para gestionar elementos con capacidad de filtrado y ordenamiento.
 *
 * @param <T> El tipo de elemento que maneja este gestor.
 */
public class GestorElementos<T> {

    /**
     * Filtra una lista de elementos según un criterio de filtro.
     *
     * @param elementos La lista de elementos a filtrar.
     * @param filtro El criterio de filtro a aplicar.
     * @return Una lista de elementos que cumplen con el criterio de filtro.
     */
    public List<T> filtrar(List<T> elementos, iFiltrado<T> filtro) {
        return elementos.stream()
                .filter(filtro::filtrar)
                .collect(Collectors.toList());
    }

    /**
     * Ordena una lista de elementos según un criterio de ordenamiento.
     *
     * @param elementos La lista de elementos a ordenar.
     * @param criterio El criterio de ordenamiento a aplicar.
     * @return Una lista de elementos ordenados según el criterio especificado.
     */
    public List<T> ordenar(List<T> elementos, iOrdenamiento<T> criterio) {
        return elementos.stream()
                .sorted(criterio::ordenar)
                .collect(Collectors.toList());
    }
}
