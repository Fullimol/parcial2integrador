/**
 * Repositorio para gestionar eventos con serialización JSON.
 */
package com.cultura.mvc;


import com.cultura.eventos.Evento;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class EventoRepositorio {
    // Lista de eventos
    private List<Evento> eventos = new ArrayList<>();
    
    // Archivo para guardar/cargar datos
    private static final String ARCHIVO_JSON = "eventos.json";
    
    // Objeto para serialización JSON
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Guardar un nuevo evento.
     * 
     * @param evento Evento a guardar
     */
    public void guardar(Evento evento) {
        eventos.add(evento);
        guardarEnJson();
    }

    /**
     * Obtener todos los eventos.
     * 
     * @return Lista de eventos
     */
    public List<Evento> buscarTodos() {
        return new ArrayList<>(eventos);
    }

    /**
     * Buscar un evento por su código.
     * 
     * @param codigo Código del evento
     * @return Optional con el evento encontrado
     */
    public Optional<Evento> buscarPorCodigo(String codigo) {
        return eventos.stream()
                .filter(e -> e.getCodigo().equals(codigo))
                .findFirst();
    }

    /**
     * Actualizar un evento existente.
     * 
     * @param eventoActualizado Evento con datos actualizados
     */
    public void actualizar(Evento eventoActualizado) {
        eventos.replaceAll(e -> 
            e.getCodigo().equals(eventoActualizado.getCodigo()) ? eventoActualizado : e
        );
        guardarEnJson();
    }

    /**
     * Eliminar un evento por su código.
     * 
     * @param codigo Código del evento a eliminar
     */
    public void eliminar(String codigo) {
        eventos.removeIf(e -> e.getCodigo().equals(codigo));
        guardarEnJson();
    }

    /**
     * Guardar eventos en archivo JSON.
     */
    private void guardarEnJson() {
        try (Writer escritor = new FileWriter(ARCHIVO_JSON)) {
            gson.toJson(eventos, escritor);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Cargar eventos desde archivo JSON.
     */
    public void cargarDesdeJson() {
        try (Reader lector = new FileReader(ARCHIVO_JSON)) {
            Type tipoListaEventos = new TypeToken<ArrayList<Evento>>(){}.getType();
            eventos = gson.fromJson(lector, tipoListaEventos);
            if (eventos == null) eventos = new ArrayList<>();
        } catch (IOException e) {
            eventos = new ArrayList<>();
        }
    }
}
