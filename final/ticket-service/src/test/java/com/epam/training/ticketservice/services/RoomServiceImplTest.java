package com.epam.training.ticketservice.services;

import com.epam.training.ticketservice.core.movie.Movie;
import com.epam.training.ticketservice.core.movie.MovieRepository;
import com.epam.training.ticketservice.core.room.Room;
import com.epam.training.ticketservice.core.room.RoomRepository;
import com.epam.training.ticketservice.services.impl.MovieServiceImpl;
import com.epam.training.ticketservice.services.impl.RoomServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

public class RoomServiceImplTest {
    RoomServiceImpl underTest;
    RoomRepository roomRepository;

    @BeforeEach
    public void init() {
        roomRepository = Mockito.mock(RoomRepository.class);
        underTest = new RoomServiceImpl(roomRepository);
    }

    @Test
    public void createRoomShouldCallRepositoryWithProperArgument(){
        underTest.createRoom("name", 10, 20);
        Mockito.verify(roomRepository).save(new Room("name", 10, 20));
    }

    @Test
    public void updateRoomShouldCallRepositoryWithProperArgumentIfOptionalIsPresent(){
        Mockito.when(roomRepository.findByName("name")).thenReturn(Optional.of(new Room("name", 10, 20)));
        underTest.updateRoom("name", 11, 22);
        Mockito.verify(roomRepository).save(new Room("name", 11, 22));
    }

    @Test
    public void deleteRoomShouldCallRepositoryWithProperArgument(){
        Mockito.when(roomRepository.findByName("name")).thenReturn(Optional.of(new Room("name", 10, 20)));
        underTest.deleteRoom("name");
        Mockito.verify(roomRepository).delete(new Room("name", 10, 20));
    }

}
