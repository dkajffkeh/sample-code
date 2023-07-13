package com.patrick.sampleactivemq.lifecycle;

import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

@Component
public class LifeCycleTest {

    private final TestService testService;

    public LifeCycleTest(TestService testService) {
        this.testService = testService;
    }

    @PreDestroy
    public void preDestroyTest() {
        testService.testBean();
        System.out.println("객체 소멸");
    }
}
