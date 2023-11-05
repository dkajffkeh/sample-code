package com.patrick.reactor.scheduler;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class SchedulerTest {

    private static final Logger log = LoggerFactory.getLogger(SchedulerTest.class);

    @Test
    void schedulerTest() {
        Flux.fromArray(new Integer[]{1, 2, 3, 4, 5, 6, 7})
                .subscribeOn(Schedulers.boundedElastic())
                // 1,2,3,4,5,6,7 Data 를 emit 하기 위한 Thread 를 지정함.
                .doOnNext(data -> log.info("data : {}",data))
                .subscribe();
        System.out.println("end");
        ;
    }

    @Test
    void publishOnTest() {
        Flux.fromArray(new Integer[] {1,2,3,4,5}) // main
                .publishOn(Schedulers.boundedElastic()) // A Thread
                .filter(data -> data > 2)// A Thread
                .map(Object::toString)// A Thread
                .doOnNext(log::info)
                .publishOn(Schedulers.boundedElastic()) // B Thread
                .doOnNext(log::info)
                .subscribe();// B Thread
    }

    @Test
    void subscribeOnTest() {
        Flux.fromArray(new Integer[] {1,2,3,4,5}) // A Thread
                .subscribeOn(Schedulers.boundedElastic()) // A Thread
                .filter(data -> data > 2)// A Thread
                .map(Object::toString)// A Thread
                .doOnNext(log::info)// A Thread
                .publishOn(Schedulers.boundedElastic()) // B Thread
                .doOnNext(log::info)
                .subscribe();// B Thread
    }

    @Test
    void subscribeOnTest2() {
        Flux.fromArray(new Integer[] {1,2,3,4,5}) // A Thread
                .doOnNext(data -> log.info(data.toString()))// A Thread
                .publishOn(Schedulers.parallel())
                .filter(data -> data > 2)// B Thread
                .map(Object::toString)// B Thread
                .doOnNext(log::info)// B Thread
                .subscribeOn(Schedulers.boundedElastic())
                .doOnNext(log::info) // B Thread
                .subscribe();// B Thread
    }

}
