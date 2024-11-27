/*
acá ya se aplico el uso de la INTERFAZ FiltroEvento mediante el lambda
*/

package com.cultura.gestores;

import com.cultura.eventos.Evento;
import java.time.LocalDate;

import java.util.List;
import java.util.stream.Collectors;


public class Ordenamiento {

    // Filtrar eventos según un FiltroEvento
    public List<Evento> filtrar(List<Evento> eventos, FiltroEvento filtro) {
        return eventos.stream()
                .filter(evento -> filtro.filtrar(evento)) // Usa el método de la interfaz funcional
                .collect(Collectors.toList());
    }

    // Ejemplo: Filtrar por fecha específica usando FiltroEvento
    public List<Evento> filtrarPorFecha(List<Evento> eventos, LocalDate fecha) {
        return filtrar(eventos, evento -> evento.getFecha().equals(fecha));
    }

    // Ejemplo: Filtrar por tipo usando FiltroEvento
    public List<Evento> filtrarPorTipo(List<Evento> eventos, Class<? extends Evento> tipoEvento) {
        return filtrar(eventos, tipoEvento::isInstance);
    }

    // Ejemplo: Filtrar por capacidad restante mínima usando FiltroEvento
    public List<Evento> filtrarPorCapacidadMaxima(List<Evento> eventos, int capacidadMinima) {
        return filtrar(eventos, evento -> evento.getCapacidadMaxima() >= capacidadMinima);
    }
}
