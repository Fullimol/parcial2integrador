package com.cultura.eventos;

import java.time.LocalDate;

public class Concierto extends Evento {

    // atributos
    private TipoEvento tipo;
    private String artistaPrincipal;
    private String generoMusical;

    // constructor:
    public Concierto(TipoEvento tipo, String codigo, String titulo, LocalDate fecha, String organizador,
            int capacidadMaxima, String artistaPrincipal, String generoMusical) {
        super(codigo, titulo, fecha, organizador, capacidadMaxima);
        this.tipo = TipoEvento.CONCIERTO;
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

    public String getArtistaPrincipal() {
        return artistaPrincipal;
    }

    public String getGeneroMusical() {
        return generoMusical;
    }

    public TipoEvento getTipo() {
        return tipo;
    }
    
    
}
