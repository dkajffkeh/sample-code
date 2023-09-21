package com.patrick.reactornettyclient.reactor.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/reactor")
public class FluxReturnController {

    @GetMapping
    public Flux<String> fluxReturn() {
        return Flux.just("Gello","World");
    }
}
