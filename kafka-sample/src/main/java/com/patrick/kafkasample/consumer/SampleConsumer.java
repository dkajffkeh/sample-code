package com.patrick.kafkasample.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class SampleConsumer {

    @KafkaListener(topics = {"test"})
    public void consumer(String message) {
        System.out.println(message);
    }

}
