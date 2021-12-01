package com.epam.training.ticketservice.core.Movie;

import java.util.Objects;

public class Movie {

    private String name;
    private String genre;
    private int minutes;

    public Movie(String name, String genre, int minutes) {
        this.name = name;
        this.genre = genre;
        this.minutes = minutes;
    }

    public Movie() {

    }

    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }

    public int getMinutes() {
        return minutes;
    }

    public void update(String name, String genre, int minutes) {
        this.name = name;
        this.genre = genre;
        this.minutes = minutes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Movie movie = (Movie) o;
        return minutes == movie.minutes && Objects.equals(name, movie.name) && Objects.equals(genre, movie.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, genre, minutes);
    }

}
