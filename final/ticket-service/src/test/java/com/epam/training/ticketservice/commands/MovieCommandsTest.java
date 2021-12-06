package com.epam.training.ticketservice.commands;

import com.epam.training.ticketservice.core.movie.Movie;
import com.epam.training.ticketservice.services.AccountService;
import com.epam.training.ticketservice.services.MovieService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

public class MovieCommandsTest {

    MovieService movieService;
    AccountService accountService;
    MovieCommands underTest;

    @BeforeEach
    public void init() {
    movieService = Mockito.mock(MovieService.class);
    accountService = Mockito.mock(AccountService.class);
    underTest = new MovieCommands(movieService, accountService);
    }

    @Test
    public void listMoviesShouldReturnStringGivenByService(){
        List<Movie> list = List.of(new Movie("name", "genre", 100));
        Mockito.when(movieService.getMovieList()).thenReturn(list);
        String returnValue = underTest.listMovies();
        Assertions.assertEquals("name (genre, 100 minutes)" + System.lineSeparator(), returnValue);
    }

    @Test
    public void createMovieShouldCallMatchingServiceMethodWithGivenArguments(){
        underTest.createMovie("name", "genre", 10);
        Mockito.verify(movieService).createMovie("name", "genre", 10);
    }

    @Test
    public void updateMovieShouldCallMatchingServiceMethodWithGivenArguments(){
        underTest.updateMovie("name", "genre", 10);
        Mockito.verify(movieService).updateMovie("name", "genre", 10);
    }

    @Test
    public void deleteMovieShouldCallMatchingServiceMethodWithGivenArguments(){
        underTest.deleteMovie("name");
        Mockito.verify(movieService).deleteMovie("name");
    }
}
