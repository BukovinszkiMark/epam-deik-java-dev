package com.epam.training.ticketservice.commands.availability;

import com.epam.training.ticketservice.core.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;

import java.util.Optional;

@RequiredArgsConstructor
public class CommandAvailability {

    public static Availability userIsAdmin(Optional<User> optional) {
        if (optional.isPresent() && optional.get().getRole().equals(User.Role.ADMIN)) {
            return Availability.available();
        }
        return Availability.unavailable("You must be an admin for this command.");
    }
}
