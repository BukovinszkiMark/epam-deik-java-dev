package com.epam.training.ticketservice.core.movie;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.util.Objects;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(unique = true)
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
