package com.epam.training.ticketservice.core.Screening;

import com.epam.training.ticketservice.core.Movie.Movie;
import com.epam.training.ticketservice.core.Room.Room;

import java.time.LocalDateTime;
import java.util.Objects;

public class Screening {

    private Movie movie;
    private Room room;
    private LocalDateTime dateTime;

    public Screening(Movie movie, Room room, LocalDateTime dateTime) {
        this.movie = movie;
        this.room = room;
        this.dateTime = dateTime;
    }

    public Screening() {

    }

    public Movie getMovie() {
        return movie;
    }

    public Room getRoom() {
        return room;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void update(Movie movie, Room room, LocalDateTime dateTime) {
        this.movie = movie;
        this.room = room;
        this.dateTime = dateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Screening screening = (Screening) o;
        return Objects.equals(movie, screening.movie) && Objects.equals(room, screening.room)
                && Objects.equals(dateTime, screening.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movie, room, dateTime);
    }
}
