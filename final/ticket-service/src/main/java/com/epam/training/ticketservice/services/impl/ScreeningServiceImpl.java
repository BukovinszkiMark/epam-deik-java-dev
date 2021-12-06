package com.epam.training.ticketservice.services.impl;

import com.epam.training.ticketservice.core.movie.Movie;
import com.epam.training.ticketservice.core.room.Room;
import com.epam.training.ticketservice.core.screening.Screening;
import com.epam.training.ticketservice.core.screening.ScreeningRepository;
import com.epam.training.ticketservice.services.ScreeningService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ScreeningServiceImpl implements ScreeningService {

    private ScreeningRepository screeningRepository;

    public ScreeningServiceImpl(ScreeningRepository screeningRepository) {
        this.screeningRepository = screeningRepository;
    }

    @Override
    public String createScreening(Movie movie, Room room, LocalDateTime dateTime) {
        if (isOverlappingAnotherScreening(movie,room, dateTime)) {
            return "There is an overlapping screening";
        }
        if (isOverlappingAnotherScreeningsBreakTime(movie,room, dateTime)) {
            return "This would start in the break period after another screening in this room";
        }
        if (movie != null && room != null) {
            screeningRepository.save(new Screening(movie, room, dateTime));
        }
        return null;
    }

    private boolean isOverlappingAnotherScreening(Movie movie, Room room, LocalDateTime dateTime) {
        for (Screening s : screeningRepository.findAll()) {
            if (s.getRoom().equals(room)) {
                LocalDateTime start = s.getDateTime();
                LocalDateTime end = start.plusMinutes(s.getMovie().getMinutes());
                if (dateTime.isAfter(start) && dateTime.isBefore(end)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isOverlappingAnotherScreeningsBreakTime(Movie movie, Room room, LocalDateTime dateTime) {
        for (Screening s : screeningRepository.findAll()) {
            if (s.getRoom().equals(room)) {
                LocalDateTime start = s.getDateTime().plusMinutes(s.getMovie().getMinutes());
                LocalDateTime endWithBreakIncluded = start.plusMinutes(10);
                if (dateTime.isAfter(start) && dateTime.isBefore(endWithBreakIncluded)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void deleteScreening(Movie movie, Room room, LocalDateTime dateTime) {
        screeningRepository.delete(get(movie, room, dateTime).get());
    }

    @Override
    public List<Screening> getScreeningList() {
        return screeningRepository.findAll();
    }

    private Optional<Screening> get(Movie movie, Room room, LocalDateTime datetime) {
        return screeningRepository.findByMovieAndRoomAndDateTime(movie, room, datetime);
    }
}
