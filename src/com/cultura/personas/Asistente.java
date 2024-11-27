package com.cultura.personas;

public class Asistente extends Persona {

    public Asistente(String nombre, String apellido) {
        super(nombre, apellido);
        this.setTipoPersona(TipoPersona.ASISTENTE);
    }

    @Override
    public void mostrarDetalles() {
        System.out.println("Asistente: " + getNombre() + " " + getApellido() + ", tipo: " + getTipoPersona());
    }
}
