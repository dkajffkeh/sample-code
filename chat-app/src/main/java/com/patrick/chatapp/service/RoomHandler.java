package com.patrick.chatapp.service;

import com.patrick.chatapp.repository.RoomRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Service
public class RoomHandler {

    private final Logger log = LoggerFactory.getLogger(RoomHandler.class);

    private final SimpMessageSendingOperations messageSendingOperations;

    private final RoomRepository roomRepository;

    public RoomHandler(SimpMessageSendingOperations messageSendingOperations,
            RoomRepository roomRepository) {
        this.messageSendingOperations = messageSendingOperations;
        this.roomRepository = roomRepository;
    }

    public void createRoom() {
        roomRepository.createRoom();
        messageSendingOperations.convertAndSend("/topic/rooms", roomRepository.getRooms());
    }
}
