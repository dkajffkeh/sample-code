package com.patrick.chatapp.api.room;

import com.patrick.chatapp.service.RoomHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    private final RoomHandler roomHandler;

    public RoomController(RoomHandler roomHandler) {
        this.roomHandler = roomHandler;
    }

    @PostMapping
    public void createRoom() {
        roomHandler.createRoom();
    }

}
