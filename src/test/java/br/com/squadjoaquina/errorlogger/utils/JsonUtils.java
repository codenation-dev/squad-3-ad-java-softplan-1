package br.com.squadjoaquina.errorlogger.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.GsonBuilder;

import java.time.LocalDateTime;

public class JsonUtils {

    public static String toJson(Object obj) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(obj);
    }

    public static String toJsonIgnoreNullFields(Object obj)
            throws JsonProcessingException {
        ObjectMapper mapper =
                new ObjectMapper().setSerializationInclusion(
                        JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsString(obj);
    }

    public static <T> T toObject(String json, Class<T> c) {
        return new GsonBuilder().registerTypeAdapter(LocalDateTime.class,
                                                     new DateConverter())
                                .create()
                                .fromJson(json, c);

    }

}
