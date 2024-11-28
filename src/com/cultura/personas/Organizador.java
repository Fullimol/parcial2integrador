package com.cultura.personas;

/**
 * Clase que representa un organizador, una subclase de Persona.
 */
public class Organizador extends Persona {

    /**
     * Constructor para crear un organizador.
     *
     * @param nombre El nombre del organizador.
     * @param apellido El apellido del organizador.
     */
    public Organizador(String nombre, String apellido) {
        super(nombre, apellido);
        this.setTipoPersona(TipoPersona.ORGANIZADOR);
    }

    /**
     * Muestra los detalles del organizador.
     */
    @Override
    public void mostrarDetalles() {
        System.out.println("Organizador: " + getNombre() + " " + getApellido() + ", tipo: " + getTipoPersona());
    }
}
