package com.cultura.eventos;

import java.time.LocalDate;
import java.util.List;

/**
 * Representa una conferencia, una subclase de Evento.
 */
public class Conferencia extends Evento {

    // Atributos
    private TipoEvento tipo;
    private String tema;
    private List<String> panelistas;

    /**
     * Constructor para crear una Conferencia.
     *
     * @param tipo El tipo de evento.
     * @param codigo El código del evento.
     * @param titulo El título del evento.
     * @param fecha La fecha del evento.
     * @param organizador El organizador del evento.
     * @param capacidadMaxima La capacidad máxima de asistentes.
     * @param tema El tema de la conferencia.
     * @param panelistas La lista de panelistas de la conferencia.
     */
    public Conferencia(TipoEvento tipo, String codigo, String titulo, LocalDate fecha, String organizador,
            int capacidadMaxima, String tema, List<String> panelistas) {
        super(codigo, titulo, fecha, organizador, capacidadMaxima);
        this.tipo = TipoEvento.CONFERENCIA;
        this.tema = tema;
        this.panelistas = panelistas;
    }

    /**
     * Obtiene la cantidad de panelistas de la conferencia.
     *
     * @return El número de panelistas.
     */
    public int cantidadPanelistas() {
        return panelistas.size();
    }

    /**
     * Obtiene el tema de la conferencia.
     *
     * @return El tema de la conferencia.
     */
    public String getTema() {
        return tema;
    }

    /**
     * Obtiene la lista de panelistas de la conferencia.
     *
     * @return La lista de panelistas.
     */
    public List<String> getPanelistas() {
        return panelistas;
    }

    /**
     * Obtiene el tipo de evento.
     *
     * @return El tipo de evento.
     */
    public TipoEvento getTipo() {
        return tipo;
    }

    /**
     * Verifica si la conferencia está llena.
     *
     * @return Siempre retorna false en esta implementación.
     */
    @Override
    public boolean estaLleno() {
        return false;
    }

    /**
     * Muestra los detalles de la conferencia.
     */
    @Override
    public void mostrarDetalles() {
        System.out.println("aca debo mostrar los detalles");
    }
}
