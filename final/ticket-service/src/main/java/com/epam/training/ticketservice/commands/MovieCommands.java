package com.epam.training.ticketservice.commands;

import com.epam.training.ticketservice.commands.availability.CommandAvailability;
import com.epam.training.ticketservice.core.movie.Movie;
import com.epam.training.ticketservice.services.AccountService;
import com.epam.training.ticketservice.services.MovieService;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

import java.util.List;

@ShellComponent
public class MovieCommands {

    MovieService movieService;
    AccountService accountService;

    public MovieCommands(MovieService movieService, AccountService accountService) {
        this.movieService = movieService;
        this.accountService = accountService;
    }

    @ShellMethodAvailability("userIsAdmin")
    @ShellMethod(key = "create movie", value = "Create new movie.")
    public void createMovie(String name, String genre, int length) {
        movieService.createMovie(name, genre, length);
    }

    @ShellMethodAvailability("userIsAdmin")
    @ShellMethod(key = "update movie", value = "Update existing movie.")
    public void updateMovie(String name, String genre, int length) {
        movieService.updateMovie(name, genre, length);
    }

    @ShellMethodAvailability("userIsAdmin")
    @ShellMethod(key = "delete movie", value = "Delete existing movie.")
    public void deleteMovie(String name) {
        movieService.deleteMovie(name);
    }

    @ShellMethod(key = "list movies", value = "List existing movies.")
    public String listMovies() {
        List<Movie> movies = movieService.getMovieList();
        if (movies.isEmpty()) {
            return "There are no movies at the moment";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Movie m : movies) {
            stringBuilder.append(m.getName())
                    .append(" (")
                    .append(m.getGenre())
                    .append(", ")
                    .append(m.getMinutes())
                    .append(" minutes)")
                    .append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }

    private Availability userIsAdmin() {
        return CommandAvailability.userIsAdmin(accountService.getCurrentUser());
    }

}
