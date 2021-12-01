package com.epam.training.ticketservice.services.impl;

import com.epam.training.ticketservice.core.Movie.Movie;
import com.epam.training.ticketservice.core.Room.Room;
import com.epam.training.ticketservice.core.Screening.Screening;
import com.epam.training.ticketservice.services.ScreeningService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ScreeningServiceImpl implements ScreeningService {

    private List<Screening> screeningList;

    @PostConstruct
    public void init() {
        this.screeningList = new ArrayList<>();
    }

    @Override
    public String createScreening(Movie movie, Room room, LocalDateTime dateTime) {
        isNotOverlappingAnotherScreeningOrBreakTime(movie,room, dateTime);
        System.out.println(movie != null);
        System.out.println(room != null);
        if (movie != null && room != null) {
            screeningList.add(new Screening(movie, room, dateTime));
        }
        return null;
    }

    private boolean isNotOverlappingAnotherScreeningOrBreakTime(Movie movie, Room room, LocalDateTime dateTime) {
        for (Screening s : screeningList) {
            LocalDateTime start = s.getDateTime();
            LocalDateTime endWithBreakIncluded = start.plusMinutes(s.getMovie().getMinutes() + 10);
            if (dateTime.isAfter(start) && dateTime.isBefore(endWithBreakIncluded)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void deleteScreening(Movie movie, Room room, LocalDateTime dateTime) {
        Screening toCompare = new Screening(movie, room, dateTime);
        this.screeningList = screeningList.stream()
                .filter(screening -> !(screening.equals(toCompare)))
                .collect(Collectors.toList());
    }

    @Override
    public List<Screening> getScreeningList() {
        return screeningList;
    }

    private Optional<Screening> get(Movie movie, Room room, LocalDateTime datetime) {
        Screening toCompare = new Screening(movie, room, datetime);
        return screeningList.stream()
                .filter(screening -> screening.equals(toCompare))
                .findFirst();
    }
}
