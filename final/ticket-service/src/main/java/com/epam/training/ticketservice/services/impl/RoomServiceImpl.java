package com.epam.training.ticketservice.services.impl;

import com.epam.training.ticketservice.core.Room.Room;
import com.epam.training.ticketservice.services.RoomService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {

    private List<Room> roomList;

    @PostConstruct
    public void init() {
        this.roomList = new ArrayList<>();
    }

    @Override
    public void createRoom(String name, int rows, int columns) {
        roomList.add(new Room(name, rows, columns));
    }

    @Override
    public void updateRoom(String name, int rows, int columns) {
        Optional<Room> room = getByName(name);
        if (room.isPresent()) {
            room.get().update(name, rows, columns);
        }
    }

    @Override
    public void deleteRoom(String name) {
        this.roomList = roomList.stream()
                .filter(room -> !(room.getName().equals(name)))
                .collect(Collectors.toList());
    }

    @Override
    public List<Room> getRoomList() {
        return roomList;
    }

    @Override
    public Optional<Room> getByName(String name) {
        return roomList.stream()
                .filter(room -> room.getName().equals(name))
                .findFirst();
    }
}
