package com.cultura.gestores;

/**
 * Interfaz funcional para definir un criterio de ordenamiento.
 *
 * @param <T> El tipo de elemento que se va a ordenar.
 */
@FunctionalInterface
public interface iOrdenamiento<T> {

    /**
     * Aplica el criterio de ordenamiento a dos elementos.
     *
     * @param elemento1 El primer elemento a comparar.
     * @param elemento2 El segundo elemento a comparar.
     * @return Un valor negativo si el primer elemento es menor que el segundo,
     *         cero si son iguales, o un valor positivo si el primer elemento es mayor que el segundo.
     */
    int ordenar(T elemento1, T elemento2);
}
