package com.epam.training.ticketservice.services;

import com.epam.training.ticketservice.core.movie.Movie;
import com.epam.training.ticketservice.core.movie.MovieRepository;
import com.epam.training.ticketservice.services.impl.MovieServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

public class MovieServiceImplTest {

    MovieServiceImpl underTest;
    MovieRepository movieRepository;

    @BeforeEach
    public void init() {
        movieRepository = Mockito.mock(MovieRepository.class);
        underTest = new MovieServiceImpl(movieRepository);
    }

    @Test
    public void createMovieShouldCallRepositoryWithProperArgument(){
        underTest.createMovie("name", "genre", 100);
        Mockito.verify(movieRepository).save(new Movie("name", "genre", 100));
    }

    @Test
    public void updateMovieShouldCallRepositoryWithProperArgumentIfOptionalIsPresent(){
        Mockito.when(movieRepository.findByName("name")).thenReturn(Optional.of(new Movie("name", "genre", 100)));
        underTest.updateMovie("name", "genrex", 200);
        Mockito.verify(movieRepository).save(new Movie("name", "genrex", 200));
    }

    @Test
    public void deleteMovieShouldCallRepositoryWithProperArgument(){
        Mockito.when(movieRepository.findByName("name")).thenReturn(Optional.of(new Movie("name", "genre", 100)));
        underTest.deleteMovie("name");
        Mockito.verify(movieRepository).delete(new Movie("name", "genre", 100));
    }

}
