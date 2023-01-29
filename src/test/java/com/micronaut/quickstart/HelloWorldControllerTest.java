package com.micronaut.quickstart;

import com.fasterxml.jackson.databind.JsonNode;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import jakarta.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
class HelloWorldControllerTest {

    @Inject
    @Client("/")
    HttpClient httpClient;

    @Test
    public void helloWorldGetTest() {
        String response = httpClient.toBlocking().retrieve("/hello");
        assertEquals("Hello World!", response);
    }

    @Test
    public void helloWorldGetTest2() {
        HttpResponse<String> response = httpClient.toBlocking().exchange("/hello", String.class);
        assertEquals("Hello World!", response.getBody().get());
        assertEquals(HttpStatus.OK, response.status());
    }

    @Test
    public void helloWorldServiceTest() {
        String response = httpClient.toBlocking().retrieve("/hello/service");
        assertEquals("Hello from Service!", response);
    }

    @Test
    public void helloFromConfigTest() {
        String response = httpClient.toBlocking().retrieve("/hello/config");
        assertEquals("Hello from application.yml", response);
    }

    @Test
    public void helloFromTranslationTest() {
        HttpResponse<?> response = httpClient.toBlocking().exchange("/hello/config/translation", JsonNode.class);
        assertEquals("{\"en\":\"Hello World\",\"ro\":\"Salut Lume\"}", response.getBody().get().toString());

    }

}
