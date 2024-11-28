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

/**
 * Repositorio para gestionar personas con serialización JSON y binaria.
 */
public class PersonaRepositorio {

    private List<Persona> personas;
    private final RepositorioGenerico<Persona> repositorioGenerico;
    private final GestorElementos<Persona> ordenamiento = new GestorElementos<>();

    /**
     * Constructor para crear un repositorio de personas.
     *
     * @param gson Una instancia de Gson configurada.
     */
    public PersonaRepositorio(Gson gson) {
        this.repositorioGenerico = new RepositorioGenerico<>(gson);
    }

    /**
     * Carga las personas desde un archivo JSON.
     */
    public void cargarPersonasDeJson() {
        List<JsonObject> jsonList = repositorioGenerico.cargarDesdeJson("personas.json");
        this.personas = repositorioGenerico.procesarJson(jsonList, "tipoPersona", "ORGANIZADOR", Organizador.class, "ASISTENTE", Asistente.class);
        System.out.println("Personas cargadas: " + personas.size());
    }

    /**
     * Carga las personas desde un archivo binario.
     *
     * @param nombreArchivoBinario El nombre del archivo binario desde el cual
     * cargar las personas.
     */
    public void cargarPersonasBinario(String nombreArchivoBinario) {
        repositorioGenerico.cargarListaBinaria(nombreArchivoBinario);
    }

    /**
     * Busca todas las personas.
     *
     * @return Una lista de todas las personas.
     */
    public List<Persona> buscarTodos() {
        return new ArrayList<>(personas);
    }

    /**
     * Guarda una lista de personas en un archivo binario.
     *
     * @param personas La lista de personas a guardar.
     */
    public void guardarPersonasABinario(List<Persona> personas) {
        repositorioGenerico.guardarListaBinario(personas, "personas.dat");
    }

    /**
     * Guarda una lista de personas en un archivo JSON.
     *
     * @param personas La lista de personas a guardar.
     */
    public void guardarEnJson(List<Persona> personas) {
        repositorioGenerico.guardarEnJson(personas, "personas.json");
    }

    /**
     * Ordena las personas por nombre.
     */
    public void ordenarPorNombre() {
        personas = ordenamiento.ordenar(personas, (p1, p2) -> p1.getNombre().compareTo(p2.getNombre()));
    }

    /**
     * Ordena las personas por apellido.
     */
    public void ordenarPorApellido() {
        personas = ordenamiento.ordenar(personas, (p1, p2) -> p1.getApellido().compareTo(p2.getApellido()));
    }
}
