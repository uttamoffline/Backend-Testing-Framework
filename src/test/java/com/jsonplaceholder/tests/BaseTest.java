package com.jsonplaceholder.tests;

import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.BeforeClass;

public class BaseTest {
    @BeforeClass
    public static void setup() {
        RestAssured.config().sslConfig(new SSLConfig().allowAllHostnames());
        RestAssured.useRelaxedHTTPSValidation();

        RestAssured.filters(new ResponseLoggingFilter(LogDetail.ALL),
                new RequestLoggingFilter(LogDetail.ALL));
    }
}
