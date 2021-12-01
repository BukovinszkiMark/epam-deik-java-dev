package com.epam.training.ticketservice.commands;

import com.epam.training.ticketservice.core.Movie.Movie;
import com.epam.training.ticketservice.core.User.User;
import com.epam.training.ticketservice.services.AccountService;
import com.epam.training.ticketservice.services.MovieService;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

import java.util.List;
import java.util.Optional;

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
        String returnString = "";
        for (Movie m : movies) {
            String lineToAdd = m.getName() + " (" + m.getGenre() + ", " + m.getMinutes() + " minutes)"
                    + System.lineSeparator();
            returnString += lineToAdd;
        }
        return returnString;
    }

    private Availability userIsAdmin() {
        Optional<User> optional = accountService.getCurrentUser();
        if (optional.isPresent() && optional.get().getRole().equals(User.Role.ADMIN)) {
            return Availability.available();
        }
        return Availability.unavailable("You must be an admin for this command.");
    }

}