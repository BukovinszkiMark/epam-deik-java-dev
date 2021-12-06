package com.epam.training.ticketservice.commands;

import com.epam.training.ticketservice.core.movie.Movie;
import com.epam.training.ticketservice.core.room.Room;
import com.epam.training.ticketservice.services.AccountService;
import com.epam.training.ticketservice.services.MovieService;
import com.epam.training.ticketservice.services.RoomService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

public class RoomCommandsTest {

    RoomService roomService;
    AccountService accountService;
    RoomCommands underTest;

    @BeforeEach
    public void init() {
    roomService = Mockito.mock(RoomService.class);
    accountService = Mockito.mock(AccountService.class);
    underTest = new RoomCommands(roomService, accountService);
    }

    @Test
    public void listRoomsShouldReturnStringGivenByService(){
        List<Room> list = List.of(new Room("name", 10, 20));
        Mockito.when(roomService.getRoomList()).thenReturn(list);
        String returnValue = underTest.listRooms();
        Assertions.assertEquals("Room name with 200 seats, 10 rows and 20 columns" + System.lineSeparator(), returnValue);
    }

    @Test
    public void createRoomShouldCallMatchingServiceMethodWithGivenArguments(){
        underTest.createRoom("name", 10, 20);
        Mockito.verify(roomService).createRoom("name", 10, 20);
    }

    @Test
    public void updateRoomShouldCallMatchingServiceMethodWithGivenArguments(){
        underTest.updateRoom("name", 10, 20);
        Mockito.verify(roomService).updateRoom("name", 10, 20);
    }

    @Test
    public void deleteRoomShouldCallMatchingServiceMethodWithGivenArguments(){
        underTest.deleteRoom("name");
        Mockito.verify(roomService).deleteRoom("name");
    }
}
