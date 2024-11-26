package com.cultura.gestores;

import com.cultura.eventos.Evento;

@FunctionalInterface
public interface FiltroEvento {

    boolean filtrar(Evento evento);
}
