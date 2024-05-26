package com.patrick.reactornettyserver.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/react")
public class MonoController {

    @GetMapping("/mono")
    public Mono<String> helloMono() throws InterruptedException {
        Thread.sleep(1000L);
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
