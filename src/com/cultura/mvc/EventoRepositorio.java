package com.cultura.mvc;

import com.cultura.eventos.Concierto;
import com.cultura.eventos.Conferencia;
import com.cultura.eventos.Evento;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Repositorio para gestionar eventos con serialización JSON.
 */
public class EventoRepositorio {

    private List<Evento> eventos;
    private final RepositorioGenerico<Evento> repositorioGenerico;
    private String nombreEventosJson = "eventos.json";

    /**
     * Constructor para crear un repositorio de eventos.
     *
     * @param gson Una instancia de Gson configurada.
     */
    public EventoRepositorio(Gson gson) {
        this.repositorioGenerico = new RepositorioGenerico<>(gson);
    }

    /**
     * Carga los eventos desde un archivo JSON.
     */
    public void cargarEventosJson() {
        List<JsonObject> jsonList = repositorioGenerico.cargarDesdeJson(nombreEventosJson);
        this.eventos = repositorioGenerico.procesarJson(jsonList, "tipo", "CONCIERTO", Concierto.class, "CONFERENCIA", Conferencia.class);
        System.out.println("Eventos cargados: " + eventos.size());
    }

    /**
     * Guarda los eventos en un archivo JSON.
     *
     * @param evento El evento a guardar.
     * @param nombreArchivo El nombre del archivo donde se guardarán los eventos.
     */
    public void guardarEvnetosJson(Evento evento, String nombreArchivo) {
        eventos.add(evento);
        repositorioGenerico.guardarEnJson(eventos, nombreEventosJson);
    }

    /**
     * Guarda una lista de eventos en un archivo binario.
     *
     * @param eventos La lista de eventos a guardar.
     * @param nombreArchivoBinario El nombre del archivo binario donde se guardarán los eventos.
     */
    public void guardaListaEventosBinario(List<Evento> eventos, String nombreArchivoBinario) {
        repositorioGenerico.guardarListaBinario(eventos, nombreArchivoBinario);
    }

    /**
     * Busca todos los eventos.
     *
     * @return Una lista de todos los eventos.
     */
    public List<Evento> buscarTodos() {
        return new ArrayList<>(eventos);
    }

    /**
     * Busca un evento por su código.
     *
     * @param codigo El código del evento a buscar.
     * @return Un Optional que contiene el evento si se encuentra, o vacío si no se encuentra.
     */
    public Optional<Evento> buscarPorCodigo(String codigo) {
        return eventos.stream()
                .filter(e -> e.getCodigo().equals(codigo))
                .findFirst();
    }

    /**
     * Actualiza un evento.
     *
     * @param eventoActualizado El evento actualizado.
     * @param nombreArchivo El nombre del archivo donde se guardarán los eventos.
     */
    public void actualizar(Evento eventoActualizado, String nombreArchivo) {
        eventos.replaceAll(e -> e.getCodigo().equals(eventoActualizado.getCodigo()) ? eventoActualizado : e);
        repositorioGenerico.guardarEnJson(eventos, nombreEventosJson);
    }

    /**
     * Elimina un evento por su código.
     *
     * @param codigo El código del evento a eliminar.
     * @param nombreArchivo El nombre del archivo donde se guardarán los eventos.
     * @return true si el evento se elimina exitosamente; false si ocurre un error.
     */
    public boolean eliminar(String codigo, String nombreArchivo) {
        try {
            boolean eliminado = eventos.removeIf(e -> e.getCodigo().equals(codigo));
            if (eliminado) {
                repositorioGenerico.guardarEnJson(eventos, nombreArchivo);
            }
            return eliminado;
        } catch (Exception e) {
            System.out.println("Error al eliminar el evento: " + e.getMessage());
            return false;
        }
    }

    /**
     * Exporta los eventos a un archivo CSV.
     */
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

    /**
     * Calcula el promedio de la capacidad máxima de todos los eventos.
     *
     * @return El promedio de la capacidad máxima.
     */
    public double calcularPromedioCapacidadMaxima() {
        int totalCapacidadMaxima = eventos.stream().mapToInt(Evento::getCapacidadMaxima).sum();
        int numeroDeEventos = eventos.size();
        return numeroDeEventos > 0 ? (double) totalCapacidadMaxima / numeroDeEventos : 0;
    }

    /**
     * Genera una cadena de texto con estadísticas del promedio de capacidad máxima de los eventos.
     *
     * @return Una cadena de texto con las estadísticas.
     */
    public String generarEstadisticasPromedio() {
        StringBuilder sb = new StringBuilder(); 
        sb.append("Total de personas por Evento:\n");
        eventos.forEach(evento -> sb.append(evento.getTitulo()).append(": ").append(evento.getCapacidadMaxima()).append(" personas\n")); // Promedio de Capacidad Máxima
        sb.append("\nPromedio de Capacidad Máxima:\n");
        sb.append(calcularPromedioCapacidadMaxima()).append(" personas\n");
        return sb.toString();
    }

    /**
     * Exporta las estadísticas de capacidad máxima a un archivo de texto.
     *
     * @param archivo El nombre del archivo donde se guardarán las estadísticas.
     */
    public void exportarEstadisticasATexto(String archivo) {
        String estadisticas = generarEstadisticasPromedio();
        try (FileWriter writer = new FileWriter(archivo)) {
            writer.write(estadisticas);
            System.out.println("Estadísticas exportadas a " + archivo);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al exportar las estadísticas: " + e.getMessage());
        }
    }
}
