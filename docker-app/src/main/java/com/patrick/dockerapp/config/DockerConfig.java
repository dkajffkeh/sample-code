package com.patrick.dockerapp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "docker.config")
public class DockerConfig {

    private String test;

    public DockerConfig() {

    }

    public DockerConfig(String test) {
        this.test = test;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    @Override
    public String toString() {
        return "DockerConfig{" +
                "test='" + test + '\'' +
                '}';
    }
}
