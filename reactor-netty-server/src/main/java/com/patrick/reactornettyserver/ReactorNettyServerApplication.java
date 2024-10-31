package com.patrick.reactornettyserver;

import com.patrick.reactornettyserver.server.TcpServerInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class ReactorNettyServerApplication {

    private final TcpServerInitializer tcpServerInitializer;

    public ReactorNettyServerApplication(TcpServerInitializer tcpServerInitializer) {
        this.tcpServerInitializer = tcpServerInitializer;
    }

    public static void main(String[] args) {
        SpringApplication.run(ReactorNettyServerApplication.class, args);
    }

    /*@EventListener(ApplicationReadyEvent.class)
    public void init() {
        tcpServerInitializer.startServer();
    }*/
}
