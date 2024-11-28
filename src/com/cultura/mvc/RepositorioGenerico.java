package com.cultura.mvc;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class RepositorioGenerico<T> {

    private final Gson gson;

    public RepositorioGenerico(Gson gson) {
        this.gson = gson;
    }

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

    //Esto es para evitar problemas al procesas datos de clases hijas.
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

    public void guardarEnJson(List<T> lista, String nombreArchivo) {
        try (Writer escritor = new FileWriter(nombreArchivo)) {
            gson.toJson(lista, escritor);
            System.out.println("Datos guardados en JSON exitosamente.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al guardar datos en JSON: " + e.getMessage());
        }
    }

    public void guardarListaBinario(List<?> lista, String nombreArchivoBinario) {
        try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(nombreArchivoBinario))) {
            salida.writeObject(lista);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<T> cargarListaBinaria(String nombreArchivoBinario) {
        try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(nombreArchivoBinario))) {
            return (List<T>) entrada.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

}
