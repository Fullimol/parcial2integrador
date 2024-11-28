package com.cultura.mvc;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Repositorio genérico para manejar operaciones de carga y guardado de datos en
 * JSON y binario.
 *
 * @param <T> El tipo de los elementos que maneja este repositorio.
 */
public class RepositorioGenerico<T> {

    private final Gson gson;

    /**
     * Constructor para crear un repositorio genérico.
     *
     * @param gson Una instancia de Gson configurada.
     */
    public RepositorioGenerico(Gson gson) {
        this.gson = gson;
    }

    /**
     * Carga una lista de objetos JSON desde un archivo.
     *
     * @param nombreArchivo El nombre del archivo JSON.
     * @return Una lista de objetos JSON.
     */
    public List<JsonObject> cargarDesdeJson(String nombreArchivo) {
        try (Reader lector = new FileReader(nombreArchivo)) {
            Type tipoLista = new TypeToken<ArrayList<JsonObject>>() {
            }.getType();
            return gson.fromJson(lector, tipoLista);
        } catch (FileNotFoundException e) {
            System.out.println("Archivo " + nombreArchivo + " no encontrado, iniciando con una lista vacía.");
            return new ArrayList<>();
        } catch (IOException e) {
            System.out.println("Error al leer el archivo " + nombreArchivo + ": " + e.getMessage());
            return new ArrayList<>();
        } catch (Exception e) {
            System.out.println("Error al procesar el archivo JSON: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Procesa una lista de objetos JSON y los convierte en una lista de objetos
     * del tipo especificado.
     *
     * @param jsonList La lista de objetos JSON.
     * @param clave La clave que contiene el tipo del objeto.
     * @param valor1 El primer valor esperado de la clave.
     * @param class1 La clase correspondiente al primer valor.
     * @param valor2 El segundo valor esperado de la clave.
     * @param class2 La clase correspondiente al segundo valor.
     * @param <T> El tipo de los objetos en la lista resultante.
     * @return Una lista de objetos del tipo especificado.
     */
    public <T> List<T> procesarJson(List<JsonObject> jsonList, String clave, String valor1, Class<? extends T> class1, String valor2, Class<? extends T> class2) {
        List<T> lista = new ArrayList<>();
        for (JsonObject jsonObj : jsonList) {
            String tipo = jsonObj.get(clave).getAsString();
            if (valor1.equalsIgnoreCase(tipo)) {
                lista.add(gson.fromJson(jsonObj, class1));
            } else if (valor2.equalsIgnoreCase(tipo)) {
                lista.add(gson.fromJson(jsonObj, class2));
            }
        }
        return lista;
    }

    /**
     * Guarda una lista de objetos en un archivo JSON.
     *
     * @param lista La lista de objetos a guardar.
     * @param nombreArchivo El nombre del archivo JSON.
     */
    public void guardarEnJson(List<T> lista, String nombreArchivo) {
        try (Writer escritor = new FileWriter(nombreArchivo)) {
            gson.toJson(lista, escritor);
            System.out.println("Datos guardados en JSON exitosamente.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al guardar datos en JSON: " + e.getMessage());
        }
    }

    /**
     * Guarda una lista de objetos en un archivo binario.
     *
     * @param lista La lista de objetos a guardar.
     * @param nombreArchivoBinario El nombre del archivo binario.
     */
    public void guardarListaBinario(List<?> lista, String nombreArchivoBinario) {
        try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(nombreArchivoBinario))) {
            salida.writeObject(lista);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Carga una lista de objetos desde un archivo binario.
     *
     * @param nombreArchivoBinario El nombre del archivo binario.
     * @return Una lista de objetos leída desde el archivo binario.
     */
    public List<T> cargarListaBinaria(String nombreArchivoBinario) {
        try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(nombreArchivoBinario))) {
            return (List<T>) entrada.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

}
