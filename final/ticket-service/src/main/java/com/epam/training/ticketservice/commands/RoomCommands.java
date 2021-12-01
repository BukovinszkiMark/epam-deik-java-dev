package com.epam.training.ticketservice.commands;

import com.epam.training.ticketservice.core.Room.Room;
import com.epam.training.ticketservice.core.User.User;
import com.epam.training.ticketservice.services.AccountService;
import com.epam.training.ticketservice.services.RoomService;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

import java.util.List;
import java.util.Optional;

@ShellComponent
public class RoomCommands {

    RoomService roomService;
    AccountService accountService;

    public RoomCommands(RoomService roomService, AccountService accountService) {
        this.roomService = roomService;
        this.accountService = accountService;
    }

    @ShellMethodAvailability("userIsAdmin")
    @ShellMethod(key = "create room", value = "Create new room.")
    public void createRoom(String name, int rows, int colums) {
        roomService.createRoom(name, rows, colums);
    }

    @ShellMethodAvailability("userIsAdmin")
    @ShellMethod(key = "update room", value = "Update existing room.")
    public void updateRoom(String name, int rows, int colums) {
        roomService.updateRoom(name, rows, colums);
    }

    @ShellMethodAvailability("userIsAdmin")
    @ShellMethod(key = "delete room", value = "Delete existing room.")
    public void deleteRoom(String name) {
        roomService.deleteRoom(name);
    }

    @ShellMethod(key = "list rooms", value = "List existing rooms.")
    public String listRoom() {
        List<Room> rooms = roomService.getRoomList();
        if (rooms.isEmpty()) {
            return "There are no rooms at the moment";
        }
        String returnString = "";
        for (Room r : rooms) {
            String lineToAdd = "Room " + r.getName() + " with " + (r.getRows() * r.getColumns()) + " seats, "
                    + r.getRows() + " rows and " + r.getColumns() + " columns" + System.lineSeparator();
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
