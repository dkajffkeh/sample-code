package com.patrick.chatapp.api;

import com.patrick.chatapp.api.payload.request.ChatSampleRequest;
import java.util.Arrays;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@Controller
public class GreetingController {

    private final SimpMessageSendingOperations messageSendingOperations;

    public GreetingController(
            SimpMessageSendingOperations messageSendingOperations) {
        this.messageSendingOperations = messageSendingOperations;
    }

    @MessageMapping("/hello")
    public void greeting(ChatSampleRequest message) throws Exception {
        messageSendingOperations.convertAndSend("/topic/chat/room/" + message.getTo() , message.getMessage());
    }

    @MessageMapping("/rooms")
    public void rooms() {
        messageSendingOperations.convertAndSend("/topic/rooms" , Arrays.asList(1,2,3,4,5));
    }
}
