package com.epam.training.ticketservice.services;

import com.epam.training.ticketservice.core.user.User;

import java.util.Optional;

public interface AccountService {

    String signInPrivileged(String username, String password);

    void signOut();

    String describeAccount();

    Optional<User> getCurrentUser();

}
