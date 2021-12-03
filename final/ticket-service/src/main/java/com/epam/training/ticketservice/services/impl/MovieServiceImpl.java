package com.epam.training.ticketservice.services.impl;

import com.epam.training.ticketservice.core.movie.Movie;
import com.epam.training.ticketservice.core.movie.MovieRepository;
import com.epam.training.ticketservice.services.MovieService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {

    private MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public void createMovie(String name, String genre, int length) {
        movieRepository.save(new Movie(name, genre, length));
    }

    @Override
    public void updateMovie(String name, String genre, int length) {
        Optional<Movie> movie = getByName(name);
        if (movie.isPresent()) {
            movie.get().update(name, genre, length);
            movieRepository.save(movie.get());
        }
    }

    @Override
    public void deleteMovie(String name) {
        movieRepository.delete(getByName(name).get());
    }

    @Override
    public List<Movie> getMovieList() {
        return movieRepository.findAll();
    }

    @Override
    public Optional<Movie> getByName(String name) {
        return movieRepository.findByName(name);
    }
}
