package com.patrick.reactornettyclient.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RequestMapping("/api/thread")
@RestController
public class ThreadTestController {

    private final WebClient webClient;

    private static final Logger log = LoggerFactory.getLogger(ThreadTestController.class);

    public ThreadTestController(
            WebClient webClient) {
        this.webClient = webClient;
    }

    @GetMapping
    public Mono<String> test() throws InterruptedException {
        log.info("test method thread name : {}", Thread.currentThread().getName());
        Thread.sleep(5000L);
        return Mono.just("hello world");
    }

}
