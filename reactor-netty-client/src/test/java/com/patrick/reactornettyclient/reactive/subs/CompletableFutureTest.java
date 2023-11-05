package com.patrick.reactornettyclient.reactive.subs;

import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class CompletableFutureTest {

    private static final Logger log = LoggerFactory.getLogger(CompletableFutureTest.class);

    @Test
    void completableFutureTest() {
        System.out.println("main1");
        System.out.println("main2");
    }

    private CompletableFuture<Integer> future1(int value) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                log.info("thread");
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("실행A" + value);
            return 40;
        });
    }

    private int syncMethod(int value) {
        try {
            log.info("thread");
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("실행B" + value);
        return 50;
    }

    @Test
    void monoTest() {
        log.info("main1");
        var completableFuture = future1(1);
        Mono<Integer> one = Mono.fromFuture(future1(2));
        Mono<Integer> two = Mono.fromFuture(future1(3));
        Mono<Integer> three = Mono.fromFuture(future1(4));
        Mono<Integer> four = Mono.fromCallable(() -> syncMethod(10)).subscribeOn(Schedulers.boundedElastic());
        System.out.println("실행시점");
        Mono.zip(one,two,three,four).doOnNext(result -> {
            System.out.println("내부실행");
            result.getT1();
            result.getT2();
            System.out.println("!!");
            System.out.println(result.getT4());
        }).block();
        System.out.println("main2");

    }




}
