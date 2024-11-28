package com.cultura.personas;

/**
 * Clase abstracta que representa una persona.
 */
public abstract class Persona {

    private String nombre;
    private String apellido;
    private TipoPersona tipoPersona;

    /**
     * Constructor para crear una persona.
     *
     * @param nombre El nombre de la persona.
     * @param apellido El apellido de la persona.
     */
    public Persona(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }

    /**
     * Obtiene el nombre de la persona.
     *
     * @return El nombre de la persona.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre de la persona.
     *
     * @param nombre El nuevo nombre de la persona.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el apellido de la persona.
     *
     * @return El apellido de la persona.
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Establece el apellido de la persona.
     *
     * @param apellido El nuevo apellido de la persona.
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * Obtiene el tipo de persona.
     *
     * @return El tipo de persona.
     */
    public TipoPersona getTipoPersona() {
        return tipoPersona;
    }

    /**
     * Establece el tipo de persona.
     *
     * @param tipo El nuevo tipo de persona.
     */
    public void setTipoPersona(TipoPersona tipo) {
        this.tipoPersona = tipo;
    }

    /**
     * Método abstracto para mostrar los detalles de la persona.
     */
    public abstract void mostrarDetalles();
}
