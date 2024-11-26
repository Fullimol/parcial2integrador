package com.cultura.eventos;

import java.time.LocalDate;

public abstract class Evento {

    //atributos
    private String codigo;
    private String titulo;
    private LocalDate fecha;
    private String organizador;
    private int capacidadMaxima;

    //constructor
    public Evento(String codigo, String titulo, LocalDate fecha, String organizador, int capacidadMaxima) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.fecha = fecha;
        this.organizador = organizador;
        this.capacidadMaxima = capacidadMaxima;
    }

    //metodos
    public abstract boolean estaLleno();

    public abstract void mostrarDetalles();
}
