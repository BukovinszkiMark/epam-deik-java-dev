package com.epam.training.ticketservice.commands.availability;

import com.epam.training.ticketservice.core.user.User;
import com.epam.training.ticketservice.services.impl.AccountServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.shell.Availability;

import java.util.Optional;

public class CommandAvailabilityTest {

    @Test
    public void userIsAdminReturnsAvailableIfCurrentUserIsAdmin(){
        Availability returnValue = CommandAvailability.userIsAdmin(Optional.of(new User("admin", "admin", User.Role.ADMIN)));
        Assertions.assertEquals(true, returnValue.isAvailable());
    }

    @Test
    public void userIsAdminReturnsUnavailableIfCurrentUserIsNotAdmin(){
        Availability returnValue = CommandAvailability.userIsAdmin(Optional.of(new User("admin", "admin", User.Role.USER)));
        Assertions.assertEquals(false, returnValue.isAvailable());
    }

    @Test
    public void userIsAdminReturnsAvailableIfCurrentUserOptionalIsEmpty(){
        Availability returnValue = CommandAvailability.userIsAdmin(Optional.empty());
        Assertions.assertEquals(false, returnValue.isAvailable());
    }

}
