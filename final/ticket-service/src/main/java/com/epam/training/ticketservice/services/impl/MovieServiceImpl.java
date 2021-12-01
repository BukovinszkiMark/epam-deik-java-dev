package com.epam.training.ticketservice.services.impl;

import com.epam.training.ticketservice.core.Movie.Movie;
import com.epam.training.ticketservice.services.MovieService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {

    private List<Movie> movieList;

    @PostConstruct
    public void init() {
        this.movieList = new ArrayList<>();
    }

    @Override
    public void createMovie(String name, String genre, int length) {
        System.out.println(name);
        System.out.println(genre);
        System.out.println(length);
        movieList.add(new Movie(name, genre, length));
    }


    @Override
    public void updateMovie(String name, String genre, int length) {
        Optional<Movie> movie = getByName(name);
        if (movie.isPresent()) {
            movie.get().update(name, genre, length);
        }
    }

    @Override
    public void deleteMovie(String name) {
        this.movieList = movieList.stream()
                .filter(movie -> !(movie.getName().equals(name)))
                .collect(Collectors.toList());
    }

    @Override
    public List<Movie> getMovieList() {
        return movieList;
    }

    @Override
    public Optional<Movie> getByName(String name) {
        return movieList.stream()
                .filter(movie -> movie.getName().equals(name))
                .findFirst();
    }

}
