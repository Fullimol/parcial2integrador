/**
 * Repositorio para gestionar eventos con serialización JSON.
 */
package com.cultura.mvc;

import com.cultura.eventos.Concierto;
import com.cultura.eventos.Conferencia;
import com.cultura.eventos.Evento;
import com.cultura.gestores.GsonConfig;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EventoRepositorio {

    // Lista de eventos
    private List<Evento> eventos = new ArrayList<>();
    private String nombreArchivoJson = "eventos.json";

    // Objeto para serialización JSON utilizando la configuración personalizada
    private final Gson gson = GsonConfig.createGson();

    private void guardarEnJson() {
        try (Writer escritor = new FileWriter(nombreArchivoJson)) {
            gson.toJson(eventos, escritor);
            System.out.println("Evento guardado en JSON");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cargarDesdeJson() {
        try (Reader lector = new FileReader(nombreArchivoJson)) {
            Type tipoListaEventos = new TypeToken<ArrayList<JsonObject>>() {
            }.getType();
            List<JsonObject> jsonList = gson.fromJson(lector, tipoListaEventos);
            eventos = new ArrayList<>();
            for (JsonObject jsonObj : jsonList) {
                String tipo = jsonObj.get("tipo").getAsString();
                if ("concierto".equalsIgnoreCase(tipo)) {
                    eventos.add(gson.fromJson(jsonObj, Concierto.class));
                } else if ("conferencia".equalsIgnoreCase(tipo)) {
                    eventos.add(gson.fromJson(jsonObj, Conferencia.class));
                }
            }
            System.out.println("Eventos cargados: " + eventos.size());
        } catch (FileNotFoundException e) {
            System.out.println("Archivo eventos.json no encontrado, iniciando con una lista vacía.");
            eventos = new ArrayList<>();
        } catch (IOException e) {
            System.out.println("Error al leer el archivo eventos.json: " + e.getMessage());
            eventos = new ArrayList<>();
        } catch (Exception e) {
            System.out.println("Error al procesar el archivo JSON: " + e.getMessage());
            eventos = new ArrayList<>();
        }
    }

    

    public void guardarListaBinario(List<Evento> eventos) {
        try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream("eventos.dat"))) {
            salida.writeObject(eventos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*     ((AUN NO SE ESTÁ USANDO))
    public List<Evento> cargarListaBinaria() {
        try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream("eventos.dat"))) {
            return (List<Evento>) entrada.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
    /*

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
        eventos.replaceAll(e -> e.getCodigo().equals(eventoActualizado.getCodigo()) ? eventoActualizado : e);
        guardarEnJson();
    }

    /**
     * Eliminar un evento por su código.
     *
     * @param codigo Código del evento a eliminar
     */
    public boolean eliminar(String codigo) {
        try {
            boolean eliminado = eventos.removeIf(e -> e.getCodigo().equals(codigo));
            if (eliminado) {
                guardarEnJson();
            }
            return eliminado;
        } catch (Exception e) {
            System.out.println("Error al eliminar el evento: " + e.getMessage());
            return false;
        }
    }

    public void exportarEventosACSV() {
        try (PrintWriter printWriter = new PrintWriter(new FileWriter("eventos.csv"))) {
            // Escribir encabezado
            printWriter.println("Tipo,Código,Título,Fecha,Organizador,Capacidad,Detalle 1,Detalle 2");

            // Escribir datos de eventos
            for (Evento evento : eventos) {
                String tipo = evento instanceof Concierto ? "Concierto" : "Conferencia";
                String detalles = "";
                if (evento instanceof Concierto) {
                    Concierto concierto = (Concierto) evento;
                    detalles = concierto.getArtistaPrincipal() + "," + concierto.getGeneroMusical();
                } else if (evento instanceof Conferencia) {
                    Conferencia conferencia = (Conferencia) evento;
                    detalles = conferencia.getTema() + "," + String.join(" | ", conferencia.getPanelistas());
                }
                printWriter.println(String.join(",",
                        tipo,
                        evento.getCodigo(),
                        evento.getTitulo(),
                        evento.getFecha().toString(),
                        evento.getOrganizador(),
                        String.valueOf(evento.getCapacidadMaxima()),
                        detalles));
            }
            System.out.println("Eventos exportados a CSV exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al exportar eventos a CSV: " + e.getMessage());
        }
    }
}
