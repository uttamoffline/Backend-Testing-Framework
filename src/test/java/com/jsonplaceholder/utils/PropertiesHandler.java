package com.jsonplaceholder.utils;

import lombok.SneakyThrows;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public enum PropertiesHandler {
    INSTANCE;

    private final String[] resources = {"remote-config.properties"};
    private Properties properties;

    @SneakyThrows
    PropertiesHandler() {
        properties = new Properties();
        ClassLoader classLoader = getClass().getClassLoader();

        for (String resource : resources) {
            try (InputStream resourceAsStream = classLoader.getResourceAsStream(resource)) {
                try (InputStreamReader reader = new InputStreamReader(resourceAsStream,
                        StandardCharsets.UTF_8)) {

                    properties.load(reader);
                }
            }
        }
    }

    public String getProperty(String propertyName) {
        return properties.getProperty(propertyName);
    }
}