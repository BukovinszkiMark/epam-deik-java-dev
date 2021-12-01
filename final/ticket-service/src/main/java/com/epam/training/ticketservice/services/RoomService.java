package com.epam.training.ticketservice.services;

import com.epam.training.ticketservice.core.Room.Room;

import java.util.List;
import java.util.Optional;

public interface RoomService {

    void createRoom(String name, int rows, int columns);

    void updateRoom(String name, int rows, int columns);

    void deleteRoom(String name);

    List<Room> getRoomList();

    Optional<Room> getByName(String name);

}
