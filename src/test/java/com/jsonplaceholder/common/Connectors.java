package com.jsonplaceholder.common;

import io.restassured.RestAssured;
import com.jsonplaceholder.utils.PropertiesHandler;

import java.util.Optional;

public class Connectors {

    public static void setServiceConnection() {
        setServicePort();
        setServiceHost();
        setServiceBasePath();
    }

    private static void setServicePort() {
        RestAssured.port = Optional.of(PropertiesHandler.INSTANCE.getProperty("servicePort"))
                .map(Integer::valueOf)
                .orElse(443);
    }

    private static void setServiceHost() {
        RestAssured.baseURI = Optional.ofNullable(PropertiesHandler.INSTANCE.getProperty("serviceHost"))
                .orElse("https://jsonplaceholder.typicode.com");
    }

    private static void setServiceBasePath() {
        RestAssured.basePath = Optional.ofNullable(PropertiesHandler.INSTANCE.getProperty("servicePath"))
                .orElse("/");
    }
}
