package com.patrick.kafkasample.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class SampleConsumer {

    @KafkaListener(topics = {"test"}, concurrency = "5")
    public void consumer(String message, Acknowledgment acknowledgment)
            throws InterruptedException {

        Thread.sleep(2000);

        System.out.println("Done!" + message);

        acknowledgment.acknowledge();
    }
}
