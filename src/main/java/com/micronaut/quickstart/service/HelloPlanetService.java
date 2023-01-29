package com.micronaut.quickstart.service;

import io.micronaut.context.annotation.Primary;
import jakarta.inject.Singleton;

@Singleton
public class HelloPlanetService implements MyService {

    @Override
    public String helloFromService() {
        return "Hello from Planet Service!";
    }
}
