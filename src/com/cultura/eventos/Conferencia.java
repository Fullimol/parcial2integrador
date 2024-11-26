package com.cultura.eventos;

import java.time.LocalDate;
import java.util.List;

public class Conferencia extends Evento {

    //atributos
    private String tema;
    private List<String> panelistas;

    //constructor
    public Conferencia(String codigo, String titulo, LocalDate fecha, String organizador,
            int capacidadMaxima, String tema, List<String> panelistas) {
        super(codigo, titulo, fecha, organizador, capacidadMaxima);
        this.tema = tema;
        this.panelistas = panelistas;
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
    
    
    public int cantidadPanelistas(){
        return panelistas.size();
    }

    public String getTema() {
        return tema;
    }

    public List<String> getPanelistas() {
        return panelistas;
    }
    
    
}
