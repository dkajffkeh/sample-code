package com.patrick.reactornettyclient.consumer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FakeMessageCreator {

    @GetMapping("/fake/{value}")
    public void testConsumer(@RequestParam("value") String value) {

    }
}
