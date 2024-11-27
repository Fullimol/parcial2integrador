package com.cultura.personas;

public abstract class Persona {

    private String nombre;
    private String apellido;
    private TipoPersona tipoPersona;

    public Persona(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public TipoPersona getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(TipoPersona tipo) {
        this.tipoPersona = tipo;
    }

    public abstract void mostrarDetalles();
}
