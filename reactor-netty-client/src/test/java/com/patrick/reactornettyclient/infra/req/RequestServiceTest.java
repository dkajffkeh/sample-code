package com.patrick.reactornettyclient.infra.req;

import java.time.Duration;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@SpringBootTest
class RequestServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestServiceTest.class);

    @Autowired
    private WebClient webClient;

    @Test
    void webClientAsyncTest() {
        Mono<String> response = holdTwo();

        response.map(res -> res + "World")
                .doOnNext(LOGGER::info)
                .publishOn(Schedulers.boundedElastic())
                .subscribe();

        holdOne()
                .publishOn(Schedulers.boundedElastic())
                .subscribe();

        call()
                .doOnNext(LOGGER::info)
                .subscribe();

        // Thread Done
        LOGGER.info("Done Complete");
    }

    private Mono<Void> holdOne() {
        return Mono.delay(Duration.ofMillis(500))
                .doOnNext(i -> LOGGER.info("Done1"))
                .then();
    }

    private Mono<String> holdTwo() {
        return Mono.delay(Duration.ofSeconds(1))
                .thenReturn("Hello World");
    }

    private Mono<String> call() {
        return webClient.get()
                .uri("/react/back")
                .retrieve()
                .bodyToMono(String.class);
    }
}
