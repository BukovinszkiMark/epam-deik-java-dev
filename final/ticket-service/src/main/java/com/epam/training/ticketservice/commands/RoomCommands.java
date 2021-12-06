package com.epam.training.ticketservice.commands;

import com.epam.training.ticketservice.commands.availability.CommandAvailability;
import com.epam.training.ticketservice.core.room.Room;
import com.epam.training.ticketservice.services.AccountService;
import com.epam.training.ticketservice.services.RoomService;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

import java.util.List;

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
    public String listRooms() {
        List<Room> rooms = roomService.getRoomList();
        if (rooms.isEmpty()) {
            return "There are no rooms at the moment";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Room r : rooms) {
            stringBuilder.append("Room ")
                    .append(r.getName())
                    .append(" with ")
                    .append(r.getRows() * r.getColumns())
                    .append(" seats, ")
                    .append(r.getRows())
                    .append(" rows and ")
                    .append(r.getColumns())
                    .append(" columns")
                    .append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }

    private Availability userIsAdmin() {
        return CommandAvailability.userIsAdmin(accountService.getCurrentUser());
    }

}
