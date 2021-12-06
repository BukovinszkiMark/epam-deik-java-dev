package com.epam.training.ticketservice.services;

import com.epam.training.ticketservice.core.movie.Movie;
import com.epam.training.ticketservice.core.room.Room;
import com.epam.training.ticketservice.core.screening.Screening;
import com.epam.training.ticketservice.core.screening.ScreeningRepository;
import com.epam.training.ticketservice.services.impl.ScreeningServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ScreeningServiceImplTest {

    ScreeningRepository screeningRepository;
    ScreeningServiceImpl underTest;

    @BeforeEach
    public void init(){
        screeningRepository = Mockito.mock(ScreeningRepository.class);
        underTest = new ScreeningServiceImpl(screeningRepository);
        List<Screening> list = List.of(new Screening(new Movie("movie1", "genre1", 60), new Room("room1", 10, 20), LocalDateTime.parse("2021-03-15 10:45", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))));
        Mockito.when(screeningRepository.findAll()).thenReturn(list);

    }

    @Test
    public void createScreeningShouldCallRepositoryWithProperArgumentIfConditionsAreMet(){
        underTest.createScreening(new Movie("movie2", "genre1", 60), new Room("room1", 10, 20), LocalDateTime.parse("2021-03-14 16:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    }

    @Test
    public void createScreeningShouldReturnWithProperMessageIfScreeningOverlapsAnotherScreening(){
        underTest.createScreening(new Movie("movie2", "genre1", 60), new Room("room1", 10, 20), LocalDateTime.parse("2021-03-15 11:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    }

    @Test
    public void createScreeningShouldReturnWithProperMessageIfScreeningOverlapsAnotherScreeningsBreakTime(){
        underTest.createScreening(new Movie("movie2", "genre1", 60), new Room("room1", 10, 20), LocalDateTime.parse("2021-03-15 11:50", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    }


}
