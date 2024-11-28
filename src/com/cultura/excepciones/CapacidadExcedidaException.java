package com.cultura.excepciones;

/**
 * Excepción lanzada cuando se excede la capacidad máxima permitida.
 */
public class CapacidadExcedidaException extends Exception {

    /**
     * Constructor para crear una excepción de capacidad excedida con un mensaje
     * específico.
     *
     * @param mensaje El mensaje de error que describe el motivo de la
     * excepción.
     */
    public CapacidadExcedidaException(String mensaje) {
        super(mensaje);
    }
}
