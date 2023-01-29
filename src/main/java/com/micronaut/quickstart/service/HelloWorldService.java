package com.micronaut.quickstart.service;

import io.micronaut.context.annotation.Primary;
import jakarta.inject.Singleton;

@Singleton
@Primary
public class HelloWorldService implements MyService {

    @Override
    public String helloFromService() {
        return "Hello from Service!";
    }
}
