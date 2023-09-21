package com.patrick.reactornettyclient.reactive.async;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CompletionStageTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(CompletionStageTest.class);

    @Test
    void thenAcceptTest() throws InterruptedException {
        
        CompletionStage<Integer> stage = Helper.finishedStage();
        stage.thenAccept(i -> {
                    LOGGER.info("in -> {}",i);
                })
                .thenAccept(j -> {
                    LOGGER.info("in -> {}",j);
                });

        LOGGER.info("동기 Blocking");
        Thread.sleep(100);
    }

    @Test
    void thenAcceptAsyncTest() throws InterruptedException {
        CompletionStage<Integer> stage = Helper.runningStage();
        stage.thenAcceptAsync(i -> {
            LOGGER.info("{} in", i);
        }).thenAcceptAsync(i -> {
            LOGGER.info("{} in", i);
        });

        Thread.sleep(2000);
    }

    static class Helper {

        public static CompletionStage<Integer> finishedStage() throws InterruptedException {
            var future = CompletableFuture.supplyAsync(() -> 1);
            Thread.sleep(100);
            return future;
        }

        public static CompletionStage<Integer> runningStage() {
            return CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                return 1;
            });
        }
    }
}
