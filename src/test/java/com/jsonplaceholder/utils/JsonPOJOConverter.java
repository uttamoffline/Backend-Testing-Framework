package com.jsonplaceholder.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

public class JsonPOJOConverter {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @SneakyThrows
    public static <T> String serializeToJson(T type) {
        return OBJECT_MAPPER.writeValueAsString(type);
    }
}
