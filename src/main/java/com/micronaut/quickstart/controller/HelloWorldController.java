package com.micronaut.quickstart.controller;

import com.micronaut.quickstart.service.HelloWorldTranslationConfig;
import com.micronaut.quickstart.service.MyService;
import io.micronaut.context.annotation.Property;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller("/hello")
public class HelloWorldController {

    private static final Logger LOG = LoggerFactory.getLogger(HelloWorldController.class);

    private final MyService service;
    private final String helloFromConfig;
    private final HelloWorldTranslationConfig helloWorldTranslationConfig;

    public HelloWorldController(MyService service,
                                @Property(name = "hello.world.message") String helloFromConfig,
                                HelloWorldTranslationConfig helloWorldTranslationConfig) {
        this.service = service;
        this.helloFromConfig = helloFromConfig;
        this.helloWorldTranslationConfig = helloWorldTranslationConfig;
    }

    @Get(processes = MediaType.TEXT_PLAIN)
    public String helloWorld() {
        LOG.info("Inside helloWorld endpoint.");
        return "Hello World!";
    }

    @Get("/service")
    public String helloWorldService() {
        LOG.info("Inside helloWorldService endpoint.");
        return service.helloFromService();
    }

    @Get(value = "/config", processes = MediaType.TEXT_PLAIN)
    public String helloConfig() {
        LOG.info("Inside helloConfig endpoint.");
        return helloFromConfig;
    }

    @Get(value = "/config/translation", processes = MediaType.APPLICATION_JSON)
    public HelloWorldTranslationConfig helloConfigTranslation() {
        LOG.info("Inside helloConfigTranslation endpoint.");
        return helloWorldTranslationConfig;
    }
}
