package com.cultura.personas;

/**
 * Clase que representa un asistente, una subclase de Persona.
 */
public class Asistente extends Persona {

    /**
     * Constructor para crear un asistente.
     *
     * @param nombre El nombre del asistente.
     * @param apellido El apellido del asistente.
     */
    public Asistente(String nombre, String apellido) {
        super(nombre, apellido);
        this.setTipoPersona(TipoPersona.ASISTENTE);
    }

    /**
     * Muestra los detalles del asistente.
     */
    @Override
    public void mostrarDetalles() {
        System.out.println("Asistente: " + getNombre() + " " + getApellido() + ", tipo: " + getTipoPersona());
    }
}
