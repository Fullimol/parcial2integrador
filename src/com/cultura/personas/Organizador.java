package com.cultura.personas;

public class Organizador extends Persona {

    public Organizador(String nombre, String apellido) {
        super(nombre, apellido);
        this.setTipoPersona(TipoPersona.ORGANIZADOR);
    }

    @Override
    public void mostrarDetalles() {
        System.out.println("Organizador: " + getNombre() + " " + getApellido() + ", tipo: " + getTipoPersona());
    }
}
