package com.patrick.reactornettyclient.reactive.subs;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.context.Context;

public class FluxTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(FluxTest.class);

    @Test
    void fluxCommon() {
        Flux.fromIterable(List.of(1,2,3,4,5))
                .subscribe(
                        integer -> LOGGER.info("value: {}", integer),
                        integer -> LOGGER.error("value: {}", integer),
                        () -> LOGGER.info("completed"),
                        Context.empty()
                        );

    }

    @Test
    void subscribeTest() {
        SubscriberTest subscriberTest = new SubscriberTest();

        Flux.fromStream(IntStream.range(0, 10).boxed())
                .buffer(3)
                .subscribe(subscriberTest);
    }

    @Test
    void fluxWithCompletableFuture() {
        Flux.create(sink -> {
            var task1 = CompletableFuture.runAsync(() -> {
                for (int i = 0; i < 5; i++) {
                    sink.next(i);
                }
            });

            var task2 = CompletableFuture.runAsync(() -> {
                for (int i = 5; i < 10; i++) {
                    sink.next(i);
                }
            });

            CompletableFuture.allOf(task1, task2)
                    .thenRun(sink::complete);
        }).subscribe(
                value -> LOGGER.info("value : {}",value),
                error -> LOGGER.info("error"),
                () -> LOGGER.info("completed")
        );
    }

    private static class SubscriberTest extends BaseSubscriber<List<Integer>> {

        private Integer count = 0;

        @Override
        protected void hookOnSubscribe(Subscription subscription) {
            request(2);
        }

        @Override
        protected void hookOnNext(List<Integer> value) {
            LOGGER.info("value:" + value);
            if (++count == 2) cancel();
        }

        @Override
        protected void hookOnComplete() {
            LOGGER.info("completed");
        }
    }

    @Test
    void subscribeMethodExample() {
        Flux<Integer> ints = Flux.range(1, 3);
        ints.subscribe(System.out::println);
    }

    @Test
    void fluxHandleErrorCase() {
        Flux<Integer> ints = Flux.range(1, 4)
                .map(i -> {
                    if (i <= 3) return i;
                    throw new RuntimeException("Got to 4");
                });
        ints.subscribe(System.out::println,
                error -> System.err.println("Error: " + error));
    }

    @Test
    void asyncTest() {
        LOGGER.info("main");
        Flux<Integer> flux = Flux
                .create(integerFluxSink -> sampleData().forEach(integerFluxSink::next))
                ;

        flux.subscribe();
        LOGGER.info("end");
    }

    public List<Integer> sampleData() {
        LOGGER.info("실행");
        return Arrays.asList(1,2,3,4,5);
    }





}
