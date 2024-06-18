package com.patrick.dockerapp.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class HelloDockerContainer {

    @GetMapping("/ping")
    public String pong() {
        return "pong";
    }

}
