package com.epam.training.ticketservice.services;

import com.epam.training.ticketservice.core.movie.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieService {

    void createMovie(String name, String genre, int length);

    void updateMovie(String name, String genre, int length);

    void deleteMovie(String name);

    List<Movie> getMovieList();

    Optional<Movie> getByName(String name);

}
