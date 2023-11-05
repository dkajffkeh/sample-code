package com.patrick.reactor.flux;

import java.util.Arrays;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class FluxSample1 {


    @Test
    void concatTest() {
        Flux<Object> flux =
                Mono.justOrEmpty(null)
                        .concatWith(Mono.justOrEmpty("Jobs"));
        flux.subscribe(System.out::println);
    }

    @Test
    void coldSeqTest() {
        Flux<String> coldFlux = Flux.fromIterable(Arrays.asList("RED", "YELLOW", "PINK"))
                .map(String::toLowerCase);

        coldFlux.subscribe(System.out::println);
        System.out.println("-----");
        coldFlux.subscribe(System.out::println);

    }

}
