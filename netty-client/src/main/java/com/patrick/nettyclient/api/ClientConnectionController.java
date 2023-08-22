package com.patrick.nettyclient.api;

import com.patrick.nettyclient.handler.SocketConnectionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/socket/connection")
public class ClientConnectionController {

    private final SocketConnectionHandler socketConnectionHandler;

    public ClientConnectionController(
            SocketConnectionHandler socketConnectionHandler) {
        this.socketConnectionHandler = socketConnectionHandler;
    }

    @PostMapping("/open")
    public void openSocketConnection() throws InterruptedException {
        socketConnectionHandler.openSocketAndSend();
    }

    @PostMapping("/send")
    public void sendData() throws InterruptedException {
        socketConnectionHandler.send();
    }
}
