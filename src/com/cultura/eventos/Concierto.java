package com.cultura.eventos;

import java.time.LocalDate;

/**
 * Representa un concierto, una subclase de Evento.
 */
public class Concierto extends Evento {

    // Atributos
    private TipoEvento tipo;
    private String artistaPrincipal;
    private String generoMusical;

    /**
     * Constructor para crear un Concierto.
     *
     * @param tipo El tipo de evento.
     * @param codigo El código del evento.
     * @param titulo El título del evento.
     * @param fecha La fecha del evento.
     * @param organizador El organizador del evento.
     * @param capacidadMaxima La capacidad máxima de asistentes.
     * @param artistaPrincipal El artista principal del concierto.
     * @param generoMusical El género musical del concierto.
     */
    public Concierto(TipoEvento tipo, String codigo, String titulo, LocalDate fecha, String organizador,
            int capacidadMaxima, String artistaPrincipal, String generoMusical) {
        super(codigo, titulo, fecha, organizador, capacidadMaxima);
        this.tipo = TipoEvento.CONCIERTO;
        this.artistaPrincipal = artistaPrincipal;
        this.generoMusical = generoMusical;
    }

    // Métodos

    /**
     * Verifica si el concierto está lleno.
     *
     * @return Siempre retorna false en esta implementación.
     */
    @Override
    public boolean estaLleno() {
        return false;
    }

    /**
     * Muestra los detalles del concierto.
     */
    @Override
    public void mostrarDetalles() {
        System.out.println("aca debo mostrar los detalles");
    }

    /**
     * Verifica si el concierto es de música clásica.
     *
     * @return true si el género musical es "Clásica", ignorando mayúsculas y minúsculas.
     */
    public boolean esMusicaClasica() {
        return "Clásica".equalsIgnoreCase(generoMusical);
    }

    /**
     * Obtiene el artista principal del concierto.
     *
     * @return El artista principal.
     */
    public String getArtistaPrincipal() {
        return artistaPrincipal;
    }

    /**
     * Obtiene el género musical del concierto.
     *
     * @return El género musical.
     */
    public String getGeneroMusical() {
        return generoMusical;
    }

    /**
     * Obtiene el tipo de evento.
     *
     * @return El tipo de evento.
     */
    public TipoEvento getTipo() {
        return tipo;
    }
}
