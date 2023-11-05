package com.patrick.reactornettyclient.reactive.async;

import static java.lang.Thread.sleep;
import static java.util.concurrent.CompletableFuture.allOf;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CompletableFutureTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(CompletionStageTest.class);

    @Test
    void runAsyncTest() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            System.out.println("1Thread: " + Thread.currentThread().getName());
        });
        future.get();
        System.out.println("2Thread: " + Thread.currentThread().getName());

    }

    @Test
    void supplyAsync() throws ExecutionException, InterruptedException {

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Thread: " + Thread.currentThread().getName();
        });

        System.out.println(future.get());
        System.out.println("Thread: " + Thread.currentThread().getName());
    }

    private CompletableFuture<Integer> methodA() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                LOGGER.info("a");
                sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;
        });
    }

    private CompletableFuture<Integer> methodB() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                LOGGER.info("b");
                sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;
        });
    }

    private CompletableFuture<Integer> methodC() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                LOGGER.info("c");
                sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;
        });
    }

    private CompletableFuture<Integer> methodD() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                LOGGER.info("d");
                sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;
        });
    }

    /**
     * Completable Future 가 중첩되었을 경우 thenCompose 를 활용할 수 있음.
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    void getFutureTest() throws ExecutionException, InterruptedException {
        var aResult = methodA();
        var bResult = methodB();
        var cResult = methodC();
        var dResult = methodD();

        int result = allOf(aResult, bResult, cResult, dResult)
                .thenApply(v -> {
                    try {
                        int a = aResult.get();
                        int b = bResult.get();
                        int c = cResult.get();
                        int d = dResult.get();
                        return a + b + c + d ;
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                        throw new RuntimeException(e);
                    }
                }).get();
        System.out.println(result);
    }
}
