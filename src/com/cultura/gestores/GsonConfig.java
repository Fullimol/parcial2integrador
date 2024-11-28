/*
ESTO ES PARA EVITAR TENER CONFLICTOS AL SERIALIZAR Y DESERIALIZAR LOS VALORS DE FORMATO DATE.
*/

package com.cultura.gestores;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.time.LocalDate;

/**
 * Configuración de Gson para evitar conflictos al serializar y deserializar valores de formato Date.
 */
public class GsonConfig {

    /**
     * Crea una instancia de Gson configurada con un adaptador para LocalDate.
     *
     * @return Una instancia de Gson configurada.
     */
    public static final Gson createGson() {
        return new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
                .create();
    }
}
