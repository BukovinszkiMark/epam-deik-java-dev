package com.epam.training.ticketservice.core.Room;

import java.util.Objects;

public class Room {

    private String name;
    private int rows;
    private int columns;

    public Room(String name, int rows, int columns) {
        this.name = name;
        this.rows = rows;
        this.columns = columns;
    }

    public Room() {

    }

    public String getName() {
        return name;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public int getSeats() {
        return rows * columns;
    }

    public void update(String name, int rows, int columns) {
        this.name = name;
        this.rows = rows;
        this.columns = columns;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Room room = (Room) o;
        return rows == room.rows && columns == room.columns && Objects.equals(name, room.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, rows, columns);
    }
}
