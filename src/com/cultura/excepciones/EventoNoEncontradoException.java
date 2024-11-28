package com.cultura.excepciones;

/**
 * Excepción lanzada cuando no se encuentra un evento específico.
 */
public class EventoNoEncontradoException extends Exception {

    /**
     * Constructor para crear una excepción de evento no encontrado con un
     * mensaje específico.
     *
     * @param mensaje El mensaje de error que describe el motivo de la
     * excepción.
     */
    public EventoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}
