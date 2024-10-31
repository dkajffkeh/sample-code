package com.patrick.chatapp.api.room;

import com.patrick.chatapp.api.payload.request.ChatSampleRequest;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@Controller
public class RoomSubscribeController {

    private final SimpMessageSendingOperations messageSendingOperations;

    public RoomSubscribeController(
            SimpMessageSendingOperations messageSendingOperations) {
        this.messageSendingOperations = messageSendingOperations;
    }

    @MessageMapping("/room")
    public void greeting(ChatSampleRequest message) {
        messageSendingOperations.convertAndSend("/topic/chat/room/" + message.to() , message.message());
    }

}
