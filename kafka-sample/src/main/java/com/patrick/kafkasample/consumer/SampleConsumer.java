package com.patrick.kafkasample.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class SampleConsumer {

    @KafkaListener(topics = {"recv-rcs.02"})
    public void consumer(String message, Acknowledgment acknowledgment) throws InterruptedException {
        System.out.println(message);
/*        Thread.sleep(20000L);

        System.out.println("commit");*/
        acknowledgment.acknowledge();
    }
}
