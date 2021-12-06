package com.epam.training.ticketservice.commands;

import com.epam.training.ticketservice.commands.availability.CommandAvailability;
import com.epam.training.ticketservice.core.movie.Movie;
import com.epam.training.ticketservice.core.room.Room;
import com.epam.training.ticketservice.core.screening.Screening;
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
        StringBuilder stringBuilder = new StringBuilder();
        for (Screening s : screenings) {
            Movie m = s.getMovie();
            Room r = s.getRoom();
            stringBuilder.append(m.getName())
                    .append(" (")
                    .append(m.getGenre())
                    .append(", ")
                    .append(m.getMinutes())
                    .append(" minutes), screened in room ")
                    .append(r.getName())
                    .append(", at ")
                    .append(s.getDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
                    .append(System.lineSeparator());
        }
        return stringBuilder.toString();

    }

    private Availability userIsAdmin() {
        return CommandAvailability.userIsAdmin(accountService.getCurrentUser());
    }

}
