package com.patrick.dockerapp.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/thread")
public class BlockingTestController {

    private final RestTemplate restTemplate = new RestTemplate();

    private static final Logger log = LoggerFactory.getLogger(BlockingTestController.class);

    @GetMapping
    public String test() throws InterruptedException {
        log.info("test method thread name : {}", Thread.currentThread().getName());
        Thread.sleep(5000L);
        return "hello world";
    }
}
