/*
ESTO ES PARA EVITAR TENER CONFLICTOS AL SERIALIZAR Y DESERIALIZAR LOS VALORES DE FORMATO DATE.
*/

package com.cultura.gestores;

import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Adaptador para serializar y deserializar objetos de tipo LocalDate usando Gson.
 * Evita conflictos al manejar valores de fecha en formato Date.
 */
public class LocalDateTypeAdapter implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Serializa un objeto LocalDate a su representación JSON.
     *
     * @param date El objeto LocalDate a serializar.
     * @param typeOfSrc El tipo de la fuente del objeto.
     * @param context El contexto de serialización.
     * @return Un JsonElement que representa la fecha como una cadena en formato "yyyy-MM-dd".
     */
    @Override
    public JsonElement serialize(LocalDate date, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(date.format(formatter));
    }

    /**
     * Deserializa un JsonElement a un objeto LocalDate.
     *
     * @param json El JsonElement a deserializar.
     * @param typeOfT El tipo del destino del objeto.
     * @param context El contexto de deserialización.
     * @return Un objeto LocalDate representado por la cadena JSON.
     * @throws JsonParseException Si ocurre un error durante la deserialización.
     */
    @Override
    public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return LocalDate.parse(json.getAsString(), formatter);
    }
}
