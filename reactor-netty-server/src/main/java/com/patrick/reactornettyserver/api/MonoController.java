package com.patrick.reactornettyserver.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/react")
public class MonoController {

    private static final Logger log = LoggerFactory.getLogger(MonoController.class);

    @GetMapping("/mono")
    public Mono<String> helloMono() {
        log.info("Received request");
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("Responding");
        return Mono.just("Hello World");
    }

    @GetMapping("/str")
    public String helloString() throws InterruptedException {
        Thread.sleep(1000L);
        return "Hello World";
    }

    @GetMapping("/back")
    public Mono<String> call() {
        return Mono.just("Call");
    }

}
