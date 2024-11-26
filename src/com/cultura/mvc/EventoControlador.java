package com.cultura.mvc;

import com.cultura.excepciones.EventoNoEncontradoException;

public class EventoControlador {

    //atributos
    private EventoRepositorio repositorio;

    //constructor
    public EventoControlador() {
        this.repositorio = new EventoRepositorio();
        repositorio.cargarDesdeJson(); // Carga los datos al iniciar
    }

    //metodos:
    // Método que maneja la acción de mostrar todos los eventos
    public void mostrarEventos() {
        repositorio.buscarTodos().forEach(System.out::println);
    }

    // Método que maneja la acción de guardar un evento
    public void guardarEvento() {
    }

    public void modificar() {

    }

    public void ordenar() {

    }

    // Método que maneja la acción de eliminar un evento 
    public void eliminarEvento(String codigo) throws EventoNoEncontradoException {
        if (repositorio.buscarPorCodigo(codigo).isEmpty()) {
            throw new EventoNoEncontradoException("Evento no encontrado");
        }
        repositorio.eliminar(codigo);
        System.out.println("Evento eliminado: " + codigo);
    }
}
