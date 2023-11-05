package com.patrick.reactornettyclient.reactive.subs;

import java.util.concurrent.CompletableFuture;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class MonoTest {

    private static final Logger log = LoggerFactory.getLogger(MonoTest.class);

    @Test
    void monoTest() {
        Mono.fromCallable(() -> 1).subscribe(value -> {
            log.info("value fromCallable : {}", value);
        });

        Mono.fromFuture(CompletableFuture.supplyAsync(() -> {
            return 1;
        })).subscribe(value -> {
            log.info("value fromFuture : {}", value);
        });
    }

    Mono<Integer> methodA() {
        return Mono.create(sink -> {
            try {
                Thread.sleep(1500L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("methodA");
            sink.success(1);
        });
    }

    Mono<Integer> methodB() {
        return Mono.create(sink -> {
            try {
                Thread.sleep(1500L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("methodB");
            sink.success(1);
        });
    }

    Mono<Integer> methodC() {
        return Mono.create(sink -> {
            try {
                Thread.sleep(1500L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("methodC");
            sink.success(1);
        });
    }

    Flux<Integer> methodD() {
        return Flux.create(sink -> {
            try {
                Thread.sleep(1500L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("methodD");
            sink.next(1);
            sink.next(2);
            sink.next(3);

            sink.complete();
        });
    }

    @Test
    void zipTest() {
        var a = methodA().subscribeOn(Schedulers.parallel());
        var b = methodB().subscribeOn(Schedulers.parallel());
        var c = methodC().subscribeOn(Schedulers.parallel());
        var d = methodD().collectList().subscribeOn(Schedulers.parallel());
        Mono.zip(a, b, c, d)
                .flatMap(result -> {
                    log.info(result.getT1().toString());
                    log.info(result.getT2().toString());
                    log.info(result.getT3().toString());
                    return Mono.just(result.getT4());
                }).block();
    }


}
