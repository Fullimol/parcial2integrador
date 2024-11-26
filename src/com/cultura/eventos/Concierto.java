package com.cultura.eventos;

import java.time.LocalDate;

public class Concierto extends Evento {

    // atributos
    private String artistaPrincipal;
    private String generoMusical;

    // constructor:
    public Concierto(String codigo, String titulo, LocalDate fecha, String organizador,
            int capacidadMaxima, String artistaPrincipal, String generoMusical) {
        super(codigo, titulo, fecha, organizador, capacidadMaxima);
        this.artistaPrincipal = artistaPrincipal;
        this.generoMusical = generoMusical;
    }

    //metodos:
    @Override
    public boolean estaLleno() {
        return false;
    }

    @Override
    public void mostrarDetalles() {
        System.out.println("aca debo mostrar los detalles");
    }

    public boolean esMusicaClasica() {
        return "Clásica".equalsIgnoreCase(generoMusical);
    }
}
