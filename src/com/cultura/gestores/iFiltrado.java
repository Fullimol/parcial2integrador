package com.cultura.gestores;

/**
 * Interfaz funcional para definir un criterio de filtrado.
 *
 * @param <T> El tipo de elemento que se va a filtrar.
 */
@FunctionalInterface
public interface iFiltrado<T> {

    /**
     * Aplica el criterio de filtrado a un elemento.
     *
     * @param elemento El elemento a filtrar.
     * @return true si el elemento cumple con el criterio de filtrado; false en caso contrario.
     */
    boolean filtrar(T elemento);
}
