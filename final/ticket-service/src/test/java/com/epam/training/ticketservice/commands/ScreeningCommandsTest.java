package com.epam.training.ticketservice.commands;

import com.epam.training.ticketservice.core.movie.Movie;
import com.epam.training.ticketservice.core.room.Room;
import com.epam.training.ticketservice.services.impl.AccountServiceImpl;
import com.epam.training.ticketservice.services.impl.MovieServiceImpl;
import com.epam.training.ticketservice.services.impl.RoomServiceImpl;
import com.epam.training.ticketservice.services.impl.ScreeningServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class ScreeningCommandsTest {

    MovieServiceImpl movieService;
    RoomServiceImpl roomService;
    ScreeningServiceImpl screeningService;
    AccountServiceImpl accountService;
    ScreeningCommands underTest;

    @BeforeEach
    public void init(){
        movieService = Mockito.mock(MovieServiceImpl.class);
        roomService = Mockito.mock(RoomServiceImpl.class);
        screeningService = Mockito.mock(ScreeningServiceImpl.class);
        accountService = Mockito.mock(AccountServiceImpl.class);
        underTest = new ScreeningCommands(screeningService, roomService, movieService, accountService);
    }

    @Test
    public void createScreeningShouldCallMatchingServiceMethodWithCorrectArguments(){
        Movie movie = new Movie("movieName", "genre", 100);
        Room room = new Room("roomName", 10, 20);
        Mockito.when(movieService.getByName("movieName")).thenReturn(Optional.of(movie));
        Mockito.when(roomService.getByName("roomName")).thenReturn(Optional.of(room));
        underTest.createScreening("movieName", "roomName", "2021-03-15 10:45");
        Mockito.verify(screeningService).createScreening(movie, room, LocalDateTime.parse("2021-03-15 10:45", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    }

    @Test
    public void deleteScreeningShouldCallMatchingServiceMethodWithCorrectArguments(){
        Movie movie = new Movie("movieName", "genre", 100);
        Room room = new Room("roomName", 10, 20);
        Mockito.when(movieService.getByName("movieName")).thenReturn(Optional.of(movie));
        Mockito.when(roomService.getByName("roomName")).thenReturn(Optional.of(room));
        underTest.deleteScreening("movieName", "roomName", "2021-03-15 10:45");
        Mockito.verify(screeningService).deleteScreening(movie, room, LocalDateTime.parse("2021-03-15 10:45", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    }
}
