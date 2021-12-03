package com.epam.training.ticketservice.commands;

import com.epam.training.ticketservice.core.movie.Movie;
import com.epam.training.ticketservice.core.room.Room;
import com.epam.training.ticketservice.core.screening.Screening;
import com.epam.training.ticketservice.core.user.User;
import com.epam.training.ticketservice.services.AccountService;
import com.epam.training.ticketservice.services.MovieService;
import com.epam.training.ticketservice.services.RoomService;
import com.epam.training.ticketservice.services.ScreeningService;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@ShellComponent
public class ScreeningCommands {

    ScreeningService screeningService;
    RoomService roomService;
    MovieService movieService;
    AccountService accountService;

    public ScreeningCommands(ScreeningService screeningService, RoomService roomService, MovieService movieService,
                             AccountService accountService) {
        this.screeningService = screeningService;
        this.roomService = roomService;
        this.movieService = movieService;
        this.accountService = accountService;
    }

    @ShellMethodAvailability("userIsAdmin")
    @ShellMethod(key = "create screening", value = "Create new screening.")
    public String createScreening(String movieName, String roomName, String dateTimeText) {
        Movie movie = movieService.getByName(movieName).get();
        Room room = roomService.getByName(roomName).get();
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeText, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        return screeningService.createScreening(movie, room, dateTime);
    }

    @ShellMethodAvailability("userIsAdmin")
    @ShellMethod(key = "delete screening", value = "Update existing screening.")
    public void deleteScreening(String movieName, String roomName, String dateTimeText) {
        Movie movie = movieService.getByName(movieName).get();
        Room room = roomService.getByName(roomName).get();
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeText, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        screeningService.deleteScreening(movie, room, dateTime);
    }

    @ShellMethod(key = "list screenings", value = "List existing screenings.")
    public String listScreening() {
        List<Screening> screenings = screeningService.getScreeningList();
        if (screenings.isEmpty()) {
            return "There are no screenings";
        }
        String returnString = "";
        for (Screening s : screenings) {
            Movie m = s.getMovie();
            Room r = s.getRoom();
            String lineToAdd = m.getName() + " (" + m.getGenre() + ", " + m.getMinutes()
                    + " minutes), screened in room " + r.getName() + ", at "
                    + s.getDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + System.lineSeparator();
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
