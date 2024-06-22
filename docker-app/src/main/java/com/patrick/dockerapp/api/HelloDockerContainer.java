package com.patrick.dockerapp.api;

import com.patrick.dockerapp.config.DockerConfig;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class HelloDockerContainer {

    private final DockerConfig dockerConfig;

    public HelloDockerContainer(DockerConfig dockerConfig) {
        this.dockerConfig = dockerConfig;
    }

    @GetMapping("/ping")
    public String pong() {
        return "pong";
    }

    @GetMapping("/version")
    public String version() {
        return "1.0.1";
    }

    @GetMapping("/config")
    public String config() {
        return dockerConfig.toString();
    }

}
