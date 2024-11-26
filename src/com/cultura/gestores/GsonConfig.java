/*
ESTO ES PARA EVITAR TENER CONFLICTOS AL SERIALIZAR Y DESERIALIZAR LOS VALORS DE FORMATO DATE.
*/

package com.cultura.gestores;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.time.LocalDate;

public class GsonConfig {
    public static final Gson createGson() {
        return new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
                .create();
    }
}
