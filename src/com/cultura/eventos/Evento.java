package com.cultura.eventos;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Representa un evento abstracto.
 */
public abstract class Evento implements Serializable {
    private static final long serialVersionUID = 1L;
    
    // Atributos
    private String codigo;
    private String titulo;
    private LocalDate fecha;
    private String organizador;
    private int capacidadMaxima;

    /**
     * Constructor para crear un evento.
     *
     * @param codigo El código del evento.
     * @param titulo El título del evento.
     * @param fecha La fecha del evento.
     * @param organizador El organizador del evento.
     * @param capacidadMaxima La capacidad máxima de asistentes.
     */
    public Evento(String codigo, String titulo, LocalDate fecha, String organizador, int capacidadMaxima) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.fecha = fecha;
        this.organizador = organizador;
        this.capacidadMaxima = capacidadMaxima;
    }

    // Métodos

    /**
     * Verifica si el evento está lleno.
     *
     * @return true si el evento está lleno; false en caso contrario.
     */
    public abstract boolean estaLleno();

    /**
     * Muestra los detalles del evento.
     */
    public abstract void mostrarDetalles();

    /**
     * Obtiene el código del evento.
     *
     * @return El código del evento.
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Obtiene el título del evento.
     *
     * @return El título del evento.
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Obtiene la fecha del evento.
     *
     * @return La fecha del evento.
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * Obtiene el organizador del evento.
     *
     * @return El organizador del evento.
     */
    public String getOrganizador() {
        return organizador;
    }

    /**
     * Obtiene la capacidad máxima del evento.
     *
     * @return La capacidad máxima de asistentes.
     */
    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }
}
