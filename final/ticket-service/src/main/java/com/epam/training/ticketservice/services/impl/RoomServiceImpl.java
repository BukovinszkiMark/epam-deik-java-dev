package com.epam.training.ticketservice.services.impl;

import com.epam.training.ticketservice.core.room.Room;
import com.epam.training.ticketservice.core.room.RoomRepository;
import com.epam.training.ticketservice.services.RoomService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomServiceImpl implements RoomService {

    private RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public void createRoom(String name, int rows, int columns) {
        roomRepository.save(new Room(name, rows, columns));
    }

    @Override
    public void updateRoom(String name, int rows, int columns) {
        Optional<Room> room = getByName(name);
        if (room.isPresent()) {
            room.get().update(name, rows, columns);
            roomRepository.save(room.get());
        }
    }

    @Override
    public void deleteRoom(String name) {
        roomRepository.delete(getByName(name).get());
    }

    @Override
    public List<Room> getRoomList() {
        return roomRepository.findAll();
    }

    @Override
    public Optional<Room> getByName(String name) {
        return roomRepository.findByName(name);
    }
}
