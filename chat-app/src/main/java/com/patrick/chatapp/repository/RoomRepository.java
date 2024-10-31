package com.patrick.chatapp.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class RoomRepository {

    private static final List<Integer> rooms = new ArrayList<>();

    public void createRoom() {
        rooms.add(rooms.size() + 1);
    }

    public List<Integer> getRooms() {
        return rooms;
    }
}
