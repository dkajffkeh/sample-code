package com.patrick.reactor.mono;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import java.net.URI;
import java.util.Collections;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;


public class MonoSample1 {


    @Test
    void emptyTest() {
        Mono.empty()
                .subscribe(
                        System.out::println, // 비실행
                        error -> {},
                        () -> System.out.println("Hello")
                );
    }

    @Test
    void apiSample() {
        URI worldTimeUri = UriComponentsBuilder.newInstance().scheme("http")
                .host("worldtimeapi.org")
                .port(80)
                .path("/api/timezone/Asia/seoul")
                .build()
                .encode()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        Mono.just(
                restTemplate.exchange(worldTimeUri, HttpMethod.GET, new HttpEntity<String>(headers), String.class)
        )
                .map(response -> {
                    DocumentContext documentContext = JsonPath.parse(response.getBody());
                    return documentContext.<String>read("$.datetime");
                })
                .subscribe(
                        System.out::println,
                        error -> {},
                        () -> System.out.println("end")
                );
    }

}
