package br.com.squadjoaquina.errorlogger.utils;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateConverter implements JsonSerializer<LocalDateTime>,
        JsonDeserializer<LocalDateTime> {

    private static DateTimeFormatter dateTimeFormatter =
            DateTimeFormatter.ISO_DATE_TIME;

    public JsonElement serialize(LocalDateTime localDateTime, Type type,
                                 JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(localDateTime.format(dateTimeFormatter));
    }

    public LocalDateTime deserialize(JsonElement json, Type typeOfT,
                                     JsonDeserializationContext context)
            throws JsonParseException {
        return LocalDateTime.parse(json.getAsString(), dateTimeFormatter);
    }
}