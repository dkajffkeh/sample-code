package com.patrick.kafkasample.api;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public SampleProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @GetMapping("/message/{message}")
    public void sendMessage(@PathVariable(value = "message") String message) {
        kafkaTemplate.send("test", message);
        ListenableFuture<SendResult<String, String>> response = kafkaTemplate.send("recv-rcs.02", message);
        response.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onSuccess(SendResult<String, String> result) {
                System.out.println(result.getRecordMetadata().offset());
                System.out.println(result.getRecordMetadata().hasOffset());
            }

            @Override
            public void onFailure(Throwable ex) {
                System.out.println("error");
            }
        });
    }
}
