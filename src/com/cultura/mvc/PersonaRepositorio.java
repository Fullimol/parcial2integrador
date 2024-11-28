package com.cultura.mvc;

import com.cultura.gestores.GestorElementos;
import com.cultura.mvc.RepositorioGenerico;
import com.cultura.personas.Asistente;
import com.cultura.personas.Organizador;
import com.cultura.personas.Persona;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;

public class PersonaRepositorio {

    private List<Persona> personas;
    private final RepositorioGenerico<Persona> repositorioGenerico;
    private final GestorElementos<Persona> ordenamiento = new GestorElementos<>();

    public PersonaRepositorio(Gson gson) {
        this.repositorioGenerico = new RepositorioGenerico<>(gson);
    }

    public void cargarPersonasDeJson() {
        List<JsonObject> jsonList = repositorioGenerico.cargarDesdeJson("personas.json");
        this.personas = repositorioGenerico.procesarJson(jsonList, "tipoPersona", "ORGANIZADOR", Organizador.class, "ASISTENTE", Asistente.class);
        System.out.println("Personas cargadas: " + personas.size());
    }
    
    public void cargarPersonasBinario(String nombreArchivoBinario){
        repositorioGenerico.cargarListaBinaria(nombreArchivoBinario);
    }

    public List<Persona> buscarTodos() {
        return new ArrayList<>(personas);
    }

    public void guardarPersonasABinario(List<Persona> personas) {
        repositorioGenerico.guardarListaBinario(personas, "personas.dat");
    }

    public void guardarEnJson(List<Persona> personas) {
        repositorioGenerico.guardarEnJson(personas, "personas.json");
    }

    public void ordenarPorNombre() {
        personas = ordenamiento.ordenar(personas, (p1, p2) -> p1.getNombre().compareTo(p2.getNombre()));
    }
    
    public void ordenarPorApellido() {
        personas = ordenamiento.ordenar(personas, (p1, p2) -> p1.getApellido().compareTo(p2.getApellido()));
    }
}
