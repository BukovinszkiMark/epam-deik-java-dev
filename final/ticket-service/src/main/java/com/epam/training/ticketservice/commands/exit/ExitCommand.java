package com.epam.training.ticketservice.commands.exit;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class ExitCommand {

    @ShellMethod(key = "exit", value = "Exits the application.")
    public void exit() {
        System.exit(0);
    }
}
