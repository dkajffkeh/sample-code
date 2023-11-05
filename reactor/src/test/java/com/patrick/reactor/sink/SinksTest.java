package com.patrick.reactor.sink;

import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

public class SinksTest {

    @Test
    void sinksTest() throws InterruptedException {
        Flux.create(sink -> {
            IntStream.range(1,6).forEach(sink::next);

        }).subscribe(System.out::println);

        Thread.sleep(500L);
    }

}
