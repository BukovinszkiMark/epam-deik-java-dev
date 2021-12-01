package com.epam.training.ticketservice.services;

import com.epam.training.ticketservice.core.Movie.Movie;
import com.epam.training.ticketservice.core.Room.Room;
import com.epam.training.ticketservice.core.Screening.Screening;

import java.time.LocalDateTime;
import java.util.List;

public interface ScreeningService {

    String createScreening(Movie movie, Room room, LocalDateTime dateTime);

    void deleteScreening(Movie movie, Room room, LocalDateTime dateTime);

    List<Screening> getScreeningList();

}
